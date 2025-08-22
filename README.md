# Bank of Checkmarx - Core Banking Backend

The Core Banking Backend is the foundational Java Spring Boot service that powers the Bank of Checkmarx demo application. It provides essential banking business logic, transaction processing, account management, and data persistence. This service is intentionally designed with security vulnerabilities for educational and demonstration purposes.

## Purpose & Overview

This backend service serves as the central hub for all banking operations, handling core business logic that would typically be found in enterprise banking systems. It manages customer accounts, processes transactions, handles authentication, and provides secure data access patterns while deliberately incorporating common security vulnerabilities for educational purposes.

**Key Responsibilities:**
- Account management and customer data handling
- Transaction processing and validation
- Authentication and authorization services
- Data persistence and database operations
- Business rule enforcement
- Integration with external banking services
- Security policy implementation
- Audit trail and compliance logging

## Technology Stack
- **Framework**: Spring Boot 2.7.0
- **Language**: Java 11
- **Database**: PostgreSQL with Spring Data JPA
- **Security**: Spring Security with JWT authentication
- **Caching**: Redis integration
- **Messaging**: Apache Kafka for event streaming
- **Build Tool**: Maven 3.8+ with wrapper
- **ORM**: Hibernate with JPA
- **Serialization**: Jackson for JSON, XStream for XML
- **Logging**: Logback with structured logging

## Features

### Account Management
- Customer account creation and maintenance
- Account balance tracking and updates
- Account status management and lifecycle
- Multi-currency account support
- Account linking and relationship management

### Transaction Processing
- Real-time transaction processing
- Transaction validation and authorization
- Balance verification and updates
- Transaction history and audit trails
- Batch processing capabilities

### Security & Authentication
- JWT-based authentication system
- Role-based access control (RBAC)
- Session management and token validation
- Security policy enforcement
- Audit logging for compliance

### Data Management
- PostgreSQL database integration
- Transaction-safe operations
- Data consistency and integrity
- Backup and recovery procedures
- Data archival and retention policies

### Integration Services
- RESTful API endpoints
- JSON and XML data processing
- Kafka message processing
- Redis caching layer
- External service integration

## Architecture

### Service Architecture
```
Core Backend Service
├── Controllers/         # REST API endpoints
├── Services/           # Business logic layer
├── Repositories/       # Data access layer
├── Models/            # Domain entities and DTOs
├── Config/            # Configuration classes
└── Security/          # Authentication and authorization
```

### Database Schema
- **Accounts**: Customer account information
- **Transactions**: Transaction records and history
- **Users**: User authentication and profile data
- **Audit**: Security and compliance logging

### Integration Points
- **Bank API**: Authentication validation and account queries
- **Fraud Detection**: Transaction risk assessment
- **Wire Transfer**: International transfer processing
- **Mobile/Web Apps**: Account data and transaction services

## Quick Start

### Prerequisites
- Java 11 or higher
- PostgreSQL 13+
- Redis 6+
- Apache Kafka (optional, for messaging)
- Maven 3.8+ (or use included wrapper)

### Installation & Setup
```bash
# Setup using provided scripts
./scripts/setup.sh      # Unix/Linux/macOS
scripts\setup.bat       # Windows

# Manual setup
# Ensure Java 11+ is installed
java -version

# Use Maven wrapper for consistent builds
./mvnw clean install

# Start with development profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Docker Deployment
```bash
# Build Docker image
docker build -t core-backend .

# Run with Docker Compose (recommended)
docker-compose up core-backend

# Or run standalone
docker run -p 8080:8080 core-backend
```

## Configuration

### Application Profiles
- **default**: Basic configuration for local development
- **dev**: Development environment with debug logging
- **docker**: Docker container configuration
- **prod**: Production environment settings

### Environment Variables
```bash
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bankofcx
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=admin123

# Security Configuration
JWT_SECRET=your-jwt-secret-key
JWT_EXPIRATION=86400000

# Redis Configuration
SPRING_REDIS_HOST=localhost
SPRING_REDIS_PORT=6379

# Kafka Configuration
SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:9092
```

## API Endpoints

### Account Management
- `GET /api/accounts/{id}` - Get account details
- `POST /api/accounts` - Create new account
- `PUT /api/accounts/{id}` - Update account information
- `GET /api/accounts/{id}/balance` - Get account balance

### Transaction Processing
- `POST /api/transactions` - Process new transaction
- `GET /api/transactions/{id}` - Get transaction details
- `GET /api/accounts/{id}/transactions` - Get account transaction history

### Authentication
- `POST /api/auth/login` - User authentication
- `POST /api/auth/logout` - User logout
- `GET /api/auth/validate` - Token validation

## Security Vulnerabilities

⚠️ **This is an intentionally vulnerable demo application.** Do not use in production.

This service contains deliberate security vulnerabilities for educational purposes:

### Authentication & Authorization
- Weak JWT secret keys and configuration
- Insufficient session management
- Missing role-based access controls
- Inadequate password policies

### Data Security
- SQL injection vulnerabilities in queries
- Insufficient input validation
- Missing data encryption at rest
- Weak password hashing

### XML Processing
- XML External Entity (XXE) vulnerabilities
- Unsafe XML deserialization
- Missing XML schema validation
- XML bomb attacks possible

### Business Logic
- Race conditions in transaction processing
- Missing authorization checks
- Insufficient transaction validation
- Business rule bypass vulnerabilities

## Development

### Project Structure
```
CoreBackend/
├── src/main/java/com/checkmarx/bank/
│   ├── controller/      # REST controllers
│   ├── service/        # Business logic services
│   ├── repository/     # Data access repositories
│   ├── model/          # Entity models and DTOs
│   ├── config/         # Configuration classes
│   └── security/       # Security configuration
├── src/main/resources/
│   ├── application*.properties  # Configuration files
│   └── logback.xml     # Logging configuration
├── scripts/            # Development scripts
├── target/             # Build artifacts
├── pom.xml            # Maven configuration
└── Dockerfile         # Container configuration
```

### Build & Test
```bash
# Compile and run tests
./mvnw clean test

# Build JAR file
./mvnw clean package

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Generate test coverage report
./mvnw jacoco:report
```

### Database Migration
```bash
# Initialize database schema
./mvnw flyway:migrate

# Create test data
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=dev,testdata
```

## Monitoring & Observability

### Health Checks
- Spring Actuator endpoints for health monitoring
- Database connectivity verification
- Redis connection status
- Kafka broker connectivity

### Logging
- Structured logging with Logback
- Request/response logging
- Security event logging
- Error tracking and alerting

### Metrics
- Application performance metrics
- Business metrics (transactions, accounts)
- System resource monitoring
- Database performance tracking

## Production Deployment

### Performance Tuning
- JVM optimization settings
- Database connection pooling
- Redis caching strategies
- Kafka producer/consumer configuration

### Security Hardening
- Production JWT configuration
- Database security settings
- Network security configuration
- Secrets management integration

### Scaling Considerations
- Horizontal scaling with load balancers
- Database read replicas
- Distributed caching strategies
- Microservice decomposition

## Recommended Checkmarx One Configuration
- Criticality: 5
- Cloud Insights: No
- Internet-facing: No
