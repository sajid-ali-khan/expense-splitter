package org.example.expensesplitter.repository;

import org.example.expensesplitter.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{
    
}
