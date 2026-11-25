package com.expense.expense_tracker.repository;

import com.expense.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

    Optional<Expense> findByIdAndUserId(Long id, Long userId);

    List<Expense> findByCategoryIgnoreCaseAndUserId(
            String category, Long userId
    );
}