# Spring Boot User CRUD API 🚀

A robust REST API built with Spring Boot to perform Create, Read, Update, and Delete (CRUD) operations on user entities.

---

## ✨ Description

This project provides a clean and functional backend solution for user management, serving as an ideal starting point for applications that require user profile handling.

---

## 📋 Table of Contents

- [Features](#-features)
- [Technologies Used](#️-technologies-used)
- [Prerequisites](#-prerequisites)
- [Error Handling](#-error-handling)
- [Getting Started](#-getting-started)
- [Configuration](#️-configuration)

---

## 🎯 Features

-   **Create**: Register a new user.
-   **Read**: Retrieve a list of all users or a single user by their ID.
-   **Update**: Modify an existing user's information.
-   **Delete**: Remove a user from the system.
-   **Custom Exception Handling**: A global handler (`@RestControllerAdvice`) that provides consistent and meaningful JSON error responses for scenarios like "resource not found" or validation failures.
-   Built-in data validation for user inputs.

---

## 🛠️ Technologies Used

-   **Backend**:
    -   Java **21**
    -   Spring Boot **3.5.4**
    -   Spring Data JPA (Hibernate)
    -   Spring Web
    -   Spring Boot Starter Validation
    -   MapStruct **1.6.3**
    -   Lombok
-   **Database**:
    -   PostgreSQL
-   **Build Tool**:
    -   Maven
---
## ✅ Prerequisites

-   **JDK 21** or later
-   Maven 3.8 or later
-   A running instance of PostgreSQL (or your chosen database)

---
## ❗ Error Handling

The API uses a standardized format for error responses. When an error occurs, you will receive a JSON response in the following structure:

**Example 1: Resource Not Found (`404 NOT_FOUND`)**

When a request is made for a user that does not exist.

```json
{
    "status": 404,
    "type": "https://example.com/problems/user-not-found",
    "title": "User not found",
    "detail": "User not found.",
    "errors": null
}
```
**Example 2: Bad Request (`400 BAD_REQUEST`)**

When creating a user with an invalid email.

```json
{
    "status": 400,
    "type": "https://example.com/problems/argument-not-valid",
    "title": "Argument not valid",
    "detail": "Validation error. Please check fields.",
    "errors": [
        "email: Invalid email format."
    ]
}
```

## 🚀 Getting Started

Follow these instructions to get the project running on your local machine.

1.  **Clone the repository**
    ```sh
    git clone https://github.com/mattcalb/spring-boot-user-crud-api.git
    cd spring-boot-user-crud-api
    ```

2.  **Configure the database**
    Open `src/main/resources/application.properties` and update the `spring.datasource.*` properties with your database credentials.

3.  **Build the project**
    ```sh
    mvn clean install
    ```

4.  **Run the application**
    ```sh
    mvn spring-boot:run
    ```
    The API will be available at `http://localhost:8080/api/users`.

---

## ⚙️ Configuration

Your `application.properties` should look something like this:

```properties

# Database config
spring.datasource.url=jdbc:postgresql://localhost:5432/[your_database]
spring.datasource.username=[your_database_username]
spring.datasource.password=[your_database_password]
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate config
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Error handling config
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false
```
### Annotation Processors

The project uses Lombok and MapStruct. Make sure your IDE (e.g., IntelliJ IDEA) has annotation processing enabled for the `mapstruct-processor` dependency to ensure Mappers are correctly generated during compilation.
