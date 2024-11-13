FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia os arquivos do projeto para o diretório /app no container
COPY . .

# Caso esteja usando Maven, construa o projeto
RUN echo "ls -l" && ls -l
RUN echo "ls -l" ** ls -l app/

# Exponha a porta que a aplicação Spring Boot usará
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["./gradlew", "bootRun"]
