FROM eclipse-temurin:21-jre-jammy
LABEL authors="user"

WORKDIR /app
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]