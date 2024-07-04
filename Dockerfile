FROM openjdk:17-jdk-alpine

RUN addgroup -S mini-bank && adduser -S middle -G mini-bank

USER middle:mini-bank

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.backend.service.url=${BACKEND_SERVICE_URL}", "-jar", "/app.jar"]