# Kafka Consumer Failure & Resilience Lab

A production-inspired event-driven project built with Spring Boot + Spring Kafka to **reproduce real Kafka consumer failure scenarios** (offset commit issues, consumer group rebalance, parallel processing) and then **fix them with production-grade patterns** (manual ack, idempotency, retry/backoff, dead-letter topic).

## What this project demonstrates
- A baseline event-driven flow (Order -> Kafka -> Payment)
- Failure scenarios that are hard to catch in homolog environments (single pod)
- Production-ready consumer patterns:
  - Manual offset commit (ack after success)
  - Idempotent processing (event deduplication)
  - Retry with backoff
  - Dead Letter Topic (DLT)

## Architecture diagrams
### 1) Baseline
![Baseline](docs/architecture/01-baseline.png)

### 2) Failure scenario (production-like)

![Failure](docs/architecture/02-failure-scenario.png)


### 3) Production-ready solution

![Solution](docs/architecture/03-production-ready.png)

## Tech stack (planned)
- Java 21 + Spring Boot 3
- Spring Kafka
- Postgres (payments + idempotency)
- Docker Compose (local dev)
- Kubernetes (optional later, for scale/rebalance demos)
- Testcontainers (integration tests)

## Roadmap
- [ ] Repo foundation + docs
- [ ] MVP: order-service producing events
- [ ] MVP: payment-service consuming events (naive version)
- [ ] Reproduce failure scenarios (rebalance + missing commit)
- [ ] Apply production-ready fixes (manual ack + idempotency + DLT)
- [ ] Integration tests + CI
