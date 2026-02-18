package org.example.expensesplitter.service;

import java.util.ArrayList;
import java.util.List;

import org.example.expensesplitter.dtos.CreateGroupRequest;
import org.example.expensesplitter.entity.Group;
import org.example.expensesplitter.entity.Member;
import org.example.expensesplitter.repository.GroupRepository;
import org.example.expensesplitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public void createGroup(int userId, CreateGroupRequest request){
        var user = userRepository.findById(userId).get();
        var newGroup = new Group();

        newGroup.setName(request.groupName());
        newGroup.setOwner(user);
        
        List<Member> members = new ArrayList();
        for (var name: request.members()){
            var member = new Member();
            member.setName(name);
            member.setGroup(newGroup);
            members.add(member);
        }

        newGroup.setMembers(members);

        groupRepository.save(newGroup);
    }
}
