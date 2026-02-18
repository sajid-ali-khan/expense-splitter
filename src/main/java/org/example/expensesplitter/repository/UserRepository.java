package org.example.expensesplitter.repository;

import org.example.expensesplitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    boolean existsByPhone(String phone);
}
