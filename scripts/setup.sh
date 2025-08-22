#!/bin/bash
# Setup script for Core Backend

echo "Setting up Core Backend..."

# Create logs directory
mkdir -p logs

# Check if Maven wrapper exists, if not, generate it
if [ ! -f "./mvnw" ]; then
    echo "Maven wrapper not found. Please ensure Maven is installed and run:"
    echo "mvn -N io.takari:maven:wrapper"
    exit 1
fi

# Make Maven wrapper executable
chmod +x mvnw

# Download dependencies
echo "Downloading dependencies..."
./mvnw dependency:resolve

# Compile the project
echo "Compiling project..."
./mvnw clean compile

echo "Setup complete! Use './mvnw spring-boot:run' to start the application."
