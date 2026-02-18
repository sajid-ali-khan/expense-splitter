package org.example.expensesplitter.service;

import java.util.ArrayList;
import java.util.List;

import org.example.expensesplitter.dtos.CreateGroupRequest;
import org.example.expensesplitter.dtos.GroupOverviewResponse;
import org.example.expensesplitter.dtos.GroupResponse;
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

    public GroupResponse getById(int id) {
        if (!groupRepository.existsById(id)) return null;
        var group = groupRepository.findById(id).get();
        Double totalSpent = groupRepository.getTotalSpent(id);
        if (totalSpent == null) totalSpent = 0.0;
        var members = groupRepository.findAllMembersById(id);
        var history = groupRepository.findAllExpenesById(id);

        return new GroupResponse(group.getName(), totalSpent, members, history);
    }

    public List<GroupOverviewResponse> getAllGroupsByOwnerId(int ownerId){
        if (!userRepository.existsById(ownerId)) return null;
        return groupRepository.findAllGroupsByOwnerId(ownerId);
    }
}
