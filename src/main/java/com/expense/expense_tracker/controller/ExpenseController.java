package com.expense.expense_tracker.controller;

import com.expense.expense_tracker.dto.ExpenseRequest;
import com.expense.expense_tracker.dto.ExpenseResponse;
import com.expense.expense_tracker.model.User;
import com.expense.expense_tracker.repository.UserRepository;
import com.expense.expense_tracker.service.ExpenseService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<ExpenseResponse> getAllExpenses(
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.getAllExpenses(user.getId());
    }

    @GetMapping("/{id}")
    public ExpenseResponse getExpenseById(
            @PathVariable Long id,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.getExpenseById(id, user.getId());
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody ExpenseRequest request,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        ExpenseResponse response = expenseService.createExpense(
                request, user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        return expenseService.updateExpense(
                id, request, user.getId()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {

        User user = getAuthenticatedUser(authentication);

        expenseService.deleteExpense(id, user.getId());

        return ResponseEntity.ok("Expense deleted successfully");
    }

    @GetMapping("/category/{category}")
    public List<ExpenseResponse> getExpensesByCategory(
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