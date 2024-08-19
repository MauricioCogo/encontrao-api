package org.example.encontraoapi.utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object FileProvider {
    // Função para escrever um array de bytes em um arquivo
    fun writeBytesToFile(filePath: String, bytes: ByteArray) {
        val file = File(filePath)
        file.parentFile.mkdirs() // Cria os diretórios, se não existirem
        file.writeBytes(bytes)
    }

    // Função para ler um arquivo e retornar um array de bytes
    fun readBytesFromFile(filePath: String): ByteArray {
        val file = File(filePath)
        if (file.exists()) {
            return file.readBytes()
        } else {
            throw IllegalArgumentException("File not found: $filePath")
        }
    }

    // Função para escrever uma string em um arquivo
    fun writeStringToFile(filePath: String, content: String) {
        val file = File(filePath)
        file.parentFile.mkdirs() // Cria os diretórios, se não existirem
        file.writeText(content)
    }

    // Função para ler um arquivo e retornar uma string
    fun readStringFromFile(filePath: String): String {
        val file = File(filePath)
        if (file.exists()) {
            return file.readText()
        } else {
            throw IllegalArgumentException("File not found: $filePath")
        }
    }

    // Função para verificar se um arquivo existe
    fun fileExists(filePath: String): Boolean {
        return Files.exists(Paths.get(filePath))
    }

    // Função para deletar um arquivo
    fun deleteFile(filePath: String): Boolean {
        return File(filePath).delete()
    }

    // Função para criar diretórios, se necessário
    fun createDirectories(directoryPath: String) {
        val path: Path = Paths.get(directoryPath)
        if (!Files.exists(path)) {
            Files.createDirectories(path)
        }
    }
}