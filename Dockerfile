# Usar uma imagem leve do JDK 21
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado pelo build do projeto para o contêiner
COPY build/libs/*.jar app.jar

# Definir o comando de execução do Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
