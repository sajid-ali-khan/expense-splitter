package org.example.expensesplitter.dtos;

public record CreateUserRequest(
    String phone,
    String name
) {
}
