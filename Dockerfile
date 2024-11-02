FROM openjdk:17
EXPOSE 8080
ADD infra/app/encontrao-api-0.0.1-SNAPSHOT.jar encontrao-api.jar
ENTRYPOINT ["java", "-jar", "/encontrao-api.jar"]