FROM openjdk:8-jdk-alpine

USER root

WORKDIR /app

COPY target/core-backend-1.0.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"] 