# Usando uma imagem do Gradle com OpenJDK 17
FROM gradle:7.5-jdk17 AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o código fonte do projeto para o container
COPY . .

# Construindo o projeto
RUN gradle build --no-daemon

# Usando uma imagem leve do OpenJDK para rodar a aplicação
FROM openjdk:17

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o JAR da fase de build
COPY --from=build /app/build/libs/encontrao-api-0.0.1-SNAPSHOT.jar encontrao-api.jar

# Expondo a porta que a aplicação vai usar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/encontrao-api.jar"]
