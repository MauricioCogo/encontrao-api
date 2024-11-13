FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copiar o wrapper do Gradle e arquivos de configuração do projeto
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src ./src

# Garantir que o gradlew tenha permissão de execução
RUN chmod +x gradlew

# Construir o projeto (gerar o .jar) antes de rodar
RUN ./gradlew build

# Expor a porta que a aplicação Spring Boot usará
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["java", "-jar", "build/libs/nome-do-seu-jar.jar"]
