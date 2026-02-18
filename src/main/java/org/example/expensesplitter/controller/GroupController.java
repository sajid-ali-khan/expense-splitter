package org.example.expensesplitter.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.example.expensesplitter.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable("id") int id) {
        var response = groupService.getById(id);
        if (response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    
    
}
