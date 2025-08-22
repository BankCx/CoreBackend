#!/bin/bash
# Development server script for Core Backend

echo "Starting Core Backend development server..."

# Set development profile
export SPRING_PROFILES_ACTIVE=dev

# Start the Spring Boot application
./mvnw spring-boot:run
