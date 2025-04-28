# User Service - Spring Boot Microservice 🚀

## Overview
This is a simple user-service microservice built with Spring Boot 3, Java 17, and PostgreSQL.  
It supports basic CRUD operations for users.

## Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Docker / Docker Compose
- Testcontainers (for integration testing)

## Architecture
- Clean layered architecture
- Exception handling with `@ControllerAdvice`
- Profile-based configuration (`dev`, `prod`)
- Dockerized for easy deployment

## Setup Instructions

### Run locally (without Docker)

```bash
# Start PostgreSQL manually
# Then run:
./mvnw spring-boot:run
