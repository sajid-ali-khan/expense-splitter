package org.example.expensesplitter.service;

import org.example.expensesplitter.dtos.CreateUserRequest;
import org.example.expensesplitter.dtos.LoginRequest;
import org.example.expensesplitter.entity.User;
import org.example.expensesplitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(CreateUserRequest request){
        var newUser = new User();
        newUser.setPhone(request.phone());
        newUser.setName(request.name());

        userRepository.save(newUser);
    }

    public boolean loginUser(LoginRequest request){
        return userRepository.existsByPhone(request.phone());
    }
}
