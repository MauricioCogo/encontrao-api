FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o arquivo de gradle wrapper e o restante do projeto para o diretório /app no container
COPY . .

RUN chmod +x ./gradlew

# Exponha a porta que a aplicação Spring Boot usará
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["./gradlew", "bootRun"]
