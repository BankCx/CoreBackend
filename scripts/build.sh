#!/bin/bash
# Build script for Core Backend

echo "Building Core Backend..."

# Clean and package the application
./mvnw clean package -DskipTests

echo "Build complete! JAR file is in target/ directory."
