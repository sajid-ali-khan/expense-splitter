package org.example.expensesplitter.repository;

import org.example.expensesplitter.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
    
}
