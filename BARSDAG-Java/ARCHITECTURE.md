# BARSDAG - Architecture and Design

## Architecture Overview

BARSDAG uses a Layered Architecture with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                    REST Controllers (API)                    │
│  DrinkController │ SaleController │ OrderController          │
└──────────────────────────────────┬──────────────────────────┘
                                   │
┌──────────────────────────────────▼──────────────────────────┐
│                     Service Layer                            │
│  DrinkService │ SaleService │ OrderService                   │
│  DrinkServiceImpl │ SaleServiceImpl │ OrderServiceImpl         │
└──────────────────────────────────┬──────────────────────────┘
                                   │
┌──────────────────────────────────▼──────────────────────────┐
│                  Repository Layer (Data Access)              │
│  DrinkRepository │ SaleRepository │ OrderRepository          │
└──────────────────────────────────┬──────────────────────────┘
                                   │
┌──────────────────────────────────▼──────────────────────────┐
│              Database Layer (PostgreSQL / H2)                │
└─────────────────────────────────────────────────────────────┘
```

## Architecture Layers

### 1. Presentation Layer (Controllers)

Handles HTTP requests and JSON responses.

### 2. Service Layer (Business Logic)

Contains all business logic and CRUD operations.

### 3. Repository Layer (Data Access)

Abstracts database access using Spring Data JPA.

### 4. Entity/Model Layer

Represents database entities.

### 5. DTO Layer

Data Transfer Objects for safe data exchange.

### 6. Exception Handling Layer

Global exception handling.

## Design Patterns Used

- **Repository Pattern** - Data access abstraction
- **Service Pattern** - Business logic separation
- **DTO Pattern** - Data transfer safety
- **Dependency Injection** - Loose coupling
- **Exception Translation** - Error abstraction

## Technologies

- Spring Boot 4.0.0
- Spring Data JPA
- Hibernate ORM
- PostgreSQL / H2
- Jakarta Validation
- Maven

