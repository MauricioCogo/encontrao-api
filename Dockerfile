FROM openjdk:17-jdk-slim
COPY . .
RUN chmod +x gradlew
EXPOSE 8080
CMD ["./gradlew", "bootRun"]
