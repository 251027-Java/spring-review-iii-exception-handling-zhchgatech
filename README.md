[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/6b47nrUJ)
# Lab: Spring MVC - Complete Library API

## Goal
Complete the Library REST API with proper error handling, validation, and ResponseEntity usage.

## Learning Objectives
- Use ResponseEntity for HTTP response control
- Implement @ControllerAdvice for global exception handling
- Add Bean Validation annotations
- Create custom exceptions

## Pre-requisites
- Completed 2304-6-1 and 2304-6-2

## Tasks

### Task 1: Add Validation to Book Entity
Add Bean Validation annotations to the Book class:

```java
@NotBlank(message = "Title is required")
private String title;

@NotBlank(message = "Author is required")
private String author;

@Pattern(regexp = "^[0-9-]+$", message = "Invalid ISBN format")
private String isbn;
```

### Task 2: Update Controller with ResponseEntity
Modify BookController to return proper HTTP status codes:

| Scenario | Status Code |
|----------|-------------|
| Book found | 200 OK |
| Book created | 201 Created |
| Book not found | 404 Not Found |
| Validation error | 400 Bad Request |

**Starter code provided**: See `starter_code/BookController.java`

### Task 3: Create Custom Exceptions
Create these exception classes:
- `BookNotFoundException`
- `BookNotAvailableException`

### Task 4: Create Global Exception Handler
Create `GlobalExceptionHandler.java` with `@ControllerAdvice`:
- Handle BookNotFoundException → 404
- Handle BookNotAvailableException → 409 Conflict
- Handle validation errors → 400

**Starter code provided**: See `starter_code/GlobalExceptionHandler.java`

### Task 5: Create Error Response DTO
Create a standardized error response:
```java
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
```

## Deliverables
1. Book entity with validation annotations
2. Controller using ResponseEntity
3. Custom exceptions
4. Global exception handler
5. Error response DTO

## Verification
- [ ] POST with invalid data returns 400
- [ ] GET non-existent book returns 404
- [ ] Checkout unavailable book returns 409
- [ ] Valid operations return proper status codes

## Starter Code
See `starter_code/` directory
