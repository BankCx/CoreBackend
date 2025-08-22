@echo off
REM Setup script for Core Backend (Windows)

echo Setting up Core Backend...

REM Create logs directory
if not exist logs mkdir logs

REM Check if Maven wrapper exists
if not exist mvnw.cmd (
    echo Maven wrapper not found. Please ensure Maven is installed and run:
    echo mvn -N io.takari:maven:wrapper
    exit /b 1
)

REM Download dependencies
echo Downloading dependencies...
mvnw.cmd dependency:resolve

REM Compile the project
echo Compiling project...
mvnw.cmd clean compile

echo Setup complete! Use 'mvnw.cmd spring-boot:run' to start the application.
