FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/backend-1.0.0.jar ./backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
