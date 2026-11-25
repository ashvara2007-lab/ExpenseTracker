package com.expense.expense_tracker.service;

import com.expense.expense_tracker.model.Expense;
import com.expense.expense_tracker.model.User;
import com.expense.expense_tracker.repository.ExpenseRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses(Long userId) {

        return expenseRepository.findByUserId(userId);
    }

    public Expense getExpenseById(Long id, Long userId) {

        return expenseRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "Expense not found"
                        )
                );
    }

    public Expense createExpense(Expense expense, User user) {

        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public Expense updateExpense(
            Long id,
            Expense updatedExpense,
            Long userId) {

        Expense existingExpense = getExpenseById(id, userId);

        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());
        existingExpense.setDescription(updatedExpense.getDescription());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id, Long userId) {

        Expense existingExpense = getExpenseById(id, userId);

        expenseRepository.delete(existingExpense);
    }

    public List<Expense> getExpensesByCategory(
            String category,
            Long userId) {

        return expenseRepository.findByCategoryIgnoreCaseAndUserId(
                category, userId
        );
    }

    public double getTotalExpenses(Long userId) {

        return expenseRepository.findByUserId(userId)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}