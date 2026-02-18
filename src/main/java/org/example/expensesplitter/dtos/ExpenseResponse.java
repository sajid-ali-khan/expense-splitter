package org.example.expensesplitter.dtos;

import java.time.Instant;

public record ExpenseResponse(
    double value,
    String reason,
    String payerName,
    Instant payedAt
) {
    
}
