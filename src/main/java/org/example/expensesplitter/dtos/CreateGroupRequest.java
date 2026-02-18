package org.example.expensesplitter.dtos;

import java.util.List;

public record CreateGroupRequest(
    String groupName,
    List<String> members
) {
    
}
