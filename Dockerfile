# Backend Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

# Update to match the actual JAR file name
COPY target/fullstackbackend-0.0.1-SNAPSHOT.jar backend-app.jar

EXPOSE 8081

CMD ["java", "-jar", "backend-app.jar", "--server.port=8081"]
