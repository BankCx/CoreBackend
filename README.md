# Bank of CX - Core Banking Backend

This is the core banking engine responsible for account lifecycles, customer records, transaction processing, and ledger updates.

## Architecture

- Built with Spring Boot 2.7.x (intentionally using an older version for SCA demo purposes)
- PostgreSQL database for persistent storage
- REST APIs for internal service communication
- Kafka for event streaming
- Docker containerization

## Security Vulnerabilities (Demo Purposes Only)

This application intentionally contains security vulnerabilities for demonstration purposes:

1. SAST Vulnerabilities:
   - Hardcoded database credentials in application.properties
   - SQL injection vulnerability in customer search endpoint
   - Insecure deserialization in transaction processing
   - Command injection in file upload functionality
   - Insecure direct object references in account access

2. SCA Vulnerabilities:
   - Outdated Spring Boot version (2.7.x)
   - Vulnerable log4j dependency
   - Multiple outdated dependencies with known CVEs

3. Secrets Management:
   - Secrets stored in application.properties
   - API keys in source code
   - No key rotation mechanism

## Getting Started

1. Build the application:
```bash
./mvnw clean install
```

2. Run with Docker:
```bash
docker-compose up
```

## API Documentation

API documentation is available at `/swagger-ui.html` when running the application.

## Development Guidelines

- Follow the established code style
- Add unit tests for new features
- Document security vulnerabilities in code comments
- Use proper logging practices