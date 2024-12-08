# Backend Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY backend/target/backend-app.jar backend-app.jar

EXPOSE 8081

CMD ["java", "-jar", "backend-app.jar", "--server.port=8081"]
