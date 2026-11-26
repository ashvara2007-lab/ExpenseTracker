package com.expense.expense_tracker.service;

import com.expense.expense_tracker.Exception.ExpenseNotFoundException;
import com.expense.expense_tracker.dto.ExpenseRequest;
import com.expense.expense_tracker.dto.ExpenseResponse;
import com.expense.expense_tracker.model.Expense;
import com.expense.expense_tracker.model.User;
import com.expense.expense_tracker.repository.ExpenseRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    private ExpenseResponse convertToResponse(Expense expense) {

        return new ExpenseResponse(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate(),
                expense.getDescription()
        );
    }

    public List<ExpenseResponse> getAllExpenses(Long userId) {

        return expenseRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ExpenseResponse getExpenseById(Long id, Long userId) {

        Expense expense = expenseRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found")
                );

        return convertToResponse(expense);
    }

    public ExpenseResponse createExpense(
            ExpenseRequest request,
            User user) {

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
        expense.setDescription(request.getDescription());
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return convertToResponse(savedExpense);
    }

    public ExpenseResponse updateExpense(
            Long id,
            ExpenseRequest request,
            Long userId) {

        Expense existingExpense = expenseRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found")
                );

        existingExpense.setTitle(request.getTitle());
        existingExpense.setAmount(request.getAmount());
        existingExpense.setCategory(request.getCategory());
        existingExpense.setDate(request.getDate());
        existingExpense.setDescription(request.getDescription());

        Expense updatedExpense = expenseRepository.save(existingExpense);

        return convertToResponse(updatedExpense);
    }

    public void deleteExpense(Long id, Long userId) {

        Expense existingExpense = expenseRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found")
                );

        expenseRepository.delete(existingExpense);
    }

    public List<ExpenseResponse> getExpensesByCategory(
            String category,
            Long userId) {

        return expenseRepository
                .findByCategoryIgnoreCaseAndUserId(category, userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public double getTotalExpenses(Long userId) {

        return expenseRepository.findByUserId(userId)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}