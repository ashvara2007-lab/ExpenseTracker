package com.expense.expense_tracker.controller;

import com.expense.expense_tracker.model.Expense;
import com.expense.expense_tracker.model.User;
import com.expense.expense_tracker.repository.UserRepository;
import com.expense.expense_tracker.service.ExpenseService;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserRepository userRepository;

    public ExpenseController(
            ExpenseService expenseService,
            UserRepository userRepository) {

        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser(Authentication authentication) {

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    @GetMapping
    public List<Expense> getAllExpenses(Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.getAllExpenses(user.getId());
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(
            @PathVariable Long id,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.getExpenseById(id, user.getId());
    }

    @PostMapping
    public Expense createExpense(
            @RequestBody Expense expense,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.createExpense(expense, user);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.updateExpense(
                id, expense, user.getId()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        expenseService.deleteExpense(id, user.getId());

        return "Expense deleted successfully";
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(
            @PathVariable String category,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.getExpensesByCategory(
                category, user.getId()
        );
    }

    @GetMapping("/total")
    public double getTotalExpenses(Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.getTotalExpenses(user.getId());
    }
}