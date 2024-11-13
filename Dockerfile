FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia os arquivos do projeto para o diretório /app no container
COPY . .

# Listando os arquivos no diretório /app (após copiar os arquivos)
RUN echo "Listando os arquivos no diretório /app:" && ls -l

# Exponha a porta que a aplicação Spring Boot usará
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["./gradlew", "bootRun"]
