package org.example.expensesplitter.dtos;

import java.util.List;

public record AddExpenseRequest(
    String reason,
    double value,
    int payedBy,
    List<Integer> members 
) { }
