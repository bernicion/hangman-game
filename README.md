# Hangman REST API

A configurable, Spring Boot–based **Hangman** game exposed over REST, documented with **Swagger UI**.  
You can start a game, guess letters and get the game status.

---

## Features

- **REST API** for playing Hangman
- **Swagger UI** interactive documentation
- **Configurable**:
    - Number of max attempts per word
    - Word list (directly from `application.yml`)
- **OOP Core Logic** separated from REST layer
- **In-memory game store** (String → Game)
- **OpenAPI 3** spec auto-generated

---

## Requirements

- Java 17+
- Maven 3.9+
---

## Configuration

All settings live under the `hangman` prefix in `application.yml`

---
## Build
mvn clean install

---
## Run
mvn spring-boot:run

---
The SWAGGER UI can be accessed at http://localhost:8081/swagger-ui/index.html

