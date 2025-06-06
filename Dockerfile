# Intentionally vulnerable - using outdated base image
FROM openjdk:8-jdk-alpine

# Intentionally vulnerable - running as root
USER root

# Intentionally vulnerable - no healthcheck
# Intentionally vulnerable - no non-root user
# Intentionally vulnerable - no security scanning

# Copy the JAR file
COPY target/*.jar app.jar

# Intentionally vulnerable - exposing all ports
EXPOSE 8080

# Intentionally vulnerable - running as root
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Intentionally vulnerable - no resource limits
# Intentionally vulnerable - no security context
# Intentionally vulnerable - no read-only filesystem 