package org.example.expensesplitter.dtos;

import java.time.Instant;

public record GroupOverviewResponse(
    int id,
    String name,
    boolean isActive,
    Instant createdAt,
    int memberCount
) {
    
}
