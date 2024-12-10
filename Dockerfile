# Backend Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/fullstackbackend-0.0.1-SNAPSHOT.jar backend-app.jar
EXPOSE 8081
CMD ["java", "-jar", "backend-app.jar", "--server.port=8081"]