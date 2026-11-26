package com.expense.expense_tracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseResponse {

    private Long id;
    private String title;
    private double amount;
    private String category;
    private LocalDate date;
    private String description;

    public ExpenseResponse(
            Long id,
            String title,
            double amount,
            String category,
            LocalDate date,
            String description) {

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }
}