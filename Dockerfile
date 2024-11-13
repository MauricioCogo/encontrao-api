# Etapa de Build: Usando Gradle para compilar o projeto
FROM gradle:5.3.0-jdk-alpine AS TEMP_BUILD_IMAGE

# Definir o diretório de trabalho dentro do container
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copiar arquivos de configuração do Gradle para o container
COPY build.gradle.kts settings.gradle.kts $APP_HOME

# Copiar a pasta gradle e o código-fonte para o container
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src

# Alterar a propriedade para o usuário 'gradle' (para evitar permissões erradas)
USER root
RUN chown -R gradle /home/gradle/src

# Rodar o comando Gradle para build
RUN gradle build || return 0

# Copiar todos os arquivos restantes e rodar o 'clean build'
COPY . .
RUN gradle clean build

# Etapa Final: Imagem leve para rodar a aplicação
FROM adoptopenjdk/openjdk11:alpine-jre

# Definir o nome do artefato gerado e o diretório de trabalho
ENV ARTIFACT_NAME=encontrao-api-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

# Copiar o JAR gerado na etapa anterior para a etapa final
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

# Expor a porta usada pela aplicação Spring Boot
EXPOSE 8080

# Rodar a aplicação com o Java
ENTRYPOINT ["java", "-jar", "$ARTIFACT_NAME"]
