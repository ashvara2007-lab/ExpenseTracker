# Expense Tracker API

A RESTful API built with Java and Spring Boot to manage and track daily expenses.

## Features
- Create, retrieve, update and delete expenses
- Filter expenses by category
- Calculate total expenses

## Tech Stack
Java • Spring Boot • Spring Data JPA • H2 Database • Maven

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/expenses` | Create expense |
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/{id}` | Get expense by ID |
| PUT | `/api/expenses/{id}` | Update expense |
| DELETE | `/api/expenses/{id}` | Delete expense |
| GET | `/api/expenses/category/{category}` | Filter by category |
| GET | `/api/expenses/total` | Calculate total expenses |

## Getting Started

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run `ExpenseTrackerApplication.java`.
4. Access the API at `http://localhost:8082`.

## Database
Uses H2 in-memory database with the H2 Console available at `/h2-console`.

## Backend Improvements

- **DTOs (Data Transfer Objects):** Introduced `ExpenseRequest` and `ExpenseResponse` to separate API request and response data from database entities.
- **Validation:** Added request validation using `@Valid`, `@NotBlank`, `@NotNull`, and `@Positive`.
- **Custom Exception Handling:** Implemented `ExpenseNotFoundException` for handling missing expenses.
- **Global Exception Handling:** Added `GlobalExceptionHandler` using `@RestControllerAdvice` to handle validation errors and invalid request formats centrally.
- **Improved HTTP Responses:** Updated APIs to return appropriate HTTP status codes, including `201 Created`, `400 Bad Request`, and `404 Not Found`.
- **Refactoring:** Updated Controller and Service layers to use DTOs while preserving existing authentication and user-specific expense access.

### Testing

Tested using Postman:
- Expense CRUD operations
- Request validation
- Invalid request formats
- Expense not found handling
- Category filtering and total expense calculation
- User-specific data isolation

---

Built as a hands-on project to learn REST APIs and CRUD operations with Spring Boot.
