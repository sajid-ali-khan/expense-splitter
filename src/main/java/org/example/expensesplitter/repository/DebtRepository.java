package org.example.expensesplitter.repository;

import org.example.expensesplitter.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Integer>{
    
}
