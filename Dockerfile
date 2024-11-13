FROM openjdk:17-jdk-slim

WORKDIR /app
ADD . .

RUN chmod +x gradlew

EXPOSE 8080
CMD ["./gradlew", "bootRun"]
