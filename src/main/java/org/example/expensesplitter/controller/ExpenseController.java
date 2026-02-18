package org.example.expensesplitter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.example.expensesplitter.dtos.AddExpenseRequest;
import org.example.expensesplitter.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/groups/{groupId}/expenses")
@AllArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/new")
    public ResponseEntity<?> addExpenseToGroup(@PathVariable("groupId") int groupId, @RequestBody AddExpenseRequest request) {
        if (!expenseService.addExpenseToGroup(groupId, request)){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
