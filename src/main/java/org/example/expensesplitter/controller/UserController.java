package org.example.expensesplitter.controller;

import org.example.expensesplitter.dtos.CreateGroupRequest;
import org.example.expensesplitter.service.GroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final GroupService groupService;

    @PostMapping("/{id}/groups")
    public ResponseEntity<?> createGroup(
        @PathVariable("id") int id,
        @RequestBody CreateGroupRequest request) {
        groupService.createGroup(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/groups")
    public ResponseEntity<?> getAllGroups(@PathVariable("id")int id){
        var response = groupService.getAllGroupsByOwnerId(id);
        if (response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
