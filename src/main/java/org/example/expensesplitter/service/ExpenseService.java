package org.example.expensesplitter.service;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.example.expensesplitter.dtos.AddExpenseRequest;
import org.example.expensesplitter.entity.Debt;
import org.example.expensesplitter.entity.Expense;
import org.example.expensesplitter.entity.Member;
import org.example.expensesplitter.repository.ExpenseRepository;
import org.example.expensesplitter.repository.GroupRepository;
import org.example.expensesplitter.repository.MemberRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final MemberRepository memberRepository;

    public boolean addExpenseToGroup(int groupId, AddExpenseRequest request){
        if (!groupRepository.existsById(groupId) || !memberRepository.existsById(request.payedBy())) return false;
        var group = groupRepository.findById(groupId).get();
        var payer = memberRepository.findById(request.payedBy()).get();

        var membersMap = group.getMembers().stream()
        .collect(Collectors.toMap(Member :: getId, Function.identity()));

        int n = request.members().size();
        double each = 0;
        if (n > 0){
            each = request.value() / n;
        }

        var newExpense = new Expense();
        
        newExpense.setGroup(group);
        newExpense.setPayer(payer);        
        newExpense.setValue(request.value());
        newExpense.setReason(request.reason());
        newExpense.setDebts(new ArrayList());

        for (int memId: request.members()){
            var debt = new Debt();
            debt.setExpense(newExpense);
            debt.setMember(membersMap.get(memId));
            debt.setShare(each);

            newExpense.getDebts().add(debt);
        }

        return expenseRepository.save(newExpense) != null;
    }
}
