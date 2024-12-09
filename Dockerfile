FROM maven:3.9.4-eclipse-temurin-17
WORKDIR /app
COPY . .
EXPOSE 8081
ENTRYPOINT ["mvn", "spring-boot:run"]
