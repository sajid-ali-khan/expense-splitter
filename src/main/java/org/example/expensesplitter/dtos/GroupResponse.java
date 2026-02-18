package org.example.expensesplitter.dtos;

import java.util.List;

public record GroupResponse(
    String groupName,
    double totalSpent,
    List<MemberResponse> members,
    List<ExpenseResponse> history
) {
    
}
