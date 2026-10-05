# NordFlow

[![Tests](https://github.com/HuckerDuck/NordFlow/actions/workflows/tests.yml/badge.svg?branch=development)](https://github.com/HuckerDuck/NordFlow/actions/workflows/tests.yml)

Event-driven order and inventory platform for a Nordic PC components store.

Customers browse graphics cards, CPUs and other parts, place orders and follow the order status. Behind the scenes the services talk to each other through Kafka events, so an order is only confirmed when the stock has actually been reserved.

## Architecture

```
                 ┌──────────────┐
                 │   frontend   │  React + TypeScript
                 └──────┬───────┘
                        │ REST + JWT
       ┌────────────────┼─────────────────────┐
       ▼                ▼                     ▼
┌──────────────┐  ┌───────────────┐    ┌───────────────────┐
│ auth-service │  │ order-service │    │ inventory-service │
│ users, JWT   │  │ orders,status │    │ products, stock   │
└──────┬───────┘  └───────┬───────┘    └─────────┬─────────┘
       │                  │      Kafka           │
       │                  └──────────────────────┘
       ▼                  ▼                      ▼
   PostgreSQL         PostgreSQL             PostgreSQL
```

Each service owns its own database. No service reads another service's tables.

## Order flow

1. The customer places an order. order-service saves it as `PENDING` and publishes `OrderCreated`.
2. inventory-service tries to reserve the stock and publishes `StockReserved`, or `StockRejected` if the item is out of stock.
3. order-service marks the order as `CONFIRMED` or `REJECTED` and publishes `OrderConfirmed`.

## Services

| Service | Responsibility | Status |
|---|---|---|
| auth-service | Register, login, JWT | Working |
| inventory-service | Products and stock, reserves stock for orders | Planned |
| order-service | Place orders, order status | Planned |
| frontend | Product list, checkout, order status | Planned |

## Tech stack

- Java 21, Spring Boot 4, Spring Security, JWT
- PostgreSQL, Flyway
- Kafka
- JUnit 5, Mockito, Testcontainers
- Docker, Docker Compose, Kubernetes, GitHub Actions
- React, TypeScript, Vite

## Run locally

Requirements: JDK 21 and Docker Desktop.

1. Copy `.env.example` to `.env` and fill in your own values.
2. Start the database:
   ```bash
   docker compose up -d
   ```
3. Start the auth-service. It reads its config from environment variables, so point your run configuration to `.env` (in IntelliJ: Run configuration → Environment variables → select the file) and run `NordFlowAuthServiceApplication`.
4. Register a user:
   ```bash
   curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"email":"test@nordflow.se","password":"Test123!","firstName":"Test","lastName":"User","phoneNumber":"0701234567"}'
   ```

## Run the tests

Docker must be running, the tests start their own PostgreSQL with Testcontainers.

```bash
cd auth-service
./gradlew test
```

## API

| Method | Endpoint | Auth |
|---|---|---|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public, returns a JWT |
| GET | `/api/user/me` | Bearer token |

## Not production ready

This is a learning and portfolio project. Known gaps:

- The JWT secret is shared between services, a public key setup would be better.
- No rate limiting on login.
- No refresh tokens.
