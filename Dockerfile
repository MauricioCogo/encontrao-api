# Etapa de build: Usando o Gradle para compilar o projeto
FROM gradle:5.3.0-jdk-alpine AS TEMP_BUILD_IMAGE

# Definir o diretório do aplicativo
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copiar arquivos de configuração do Gradle para o container
COPY build.gradle.kts settings.gradle.kts $APP_HOME

# Copiar a pasta gradle e o código-fonte
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

# Rodar o Gradle para construir o projeto
RUN gradle build || return 0
COPY . . 
RUN gradle clean build

# Etapa final: Usando uma imagem leve para rodar o aplicativo
FROM adoptopenjdk/openjdk17:alpine-jre

# Definir nome do artefato e diretório do aplicativo
ENV ARTIFACT_NAME=encontrao-api-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

# Copiar o JAR gerado na etapa anterior
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

# Expor a porta para a aplicação Spring Boot
EXPOSE 8080

# Rodar a aplicação usando o Java
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
