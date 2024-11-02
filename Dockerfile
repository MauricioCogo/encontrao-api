FROM openjdk:17
EXPOSE 8080
ADD build/libs/encontrao-api-0.0.1-SNAPSHOT.jar encontrao-api.jar

ENTRYPOINT ["java", "-jar", "/encontrao-api.jar"]