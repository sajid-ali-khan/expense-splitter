package org.example.expensesplitter.repository;

import org.example.expensesplitter.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer>{
    
}
