package org.example.encontraoapi.service

import org.example.encontraoapi.utils.Base64Utils
import org.example.encontraoapi.utils.FileProvider
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FileService {
    private val storageLocation = "src/main/resources/images"

    // Salva um arquivo Base64 no sistema de arquivos e retorna o caminho do arquivo
    fun saveBase64File(base64Content: String, fileName: String): String {
        val bytes = Base64Utils.decodeToBytes(base64Content)
        val filePath = "$storageLocation/$fileName"

        val fileExists = fileExists(fileName)

        val file = File(filePath)
        if(!fileExists) {
            file.parentFile.mkdirs()
        }

        file.writeBytes(bytes)

        val imagePath = "/images/view/$fileName"
        return imagePath
    }

    // Lê um arquivo e retorna seu conteúdo codificado em Base64
    fun readFileAsBase64(fileName: String): String {
        val filePath = "$storageLocation/$fileName"
        val fileBytes = FileProvider.readBytesFromFile(filePath)
        return Base64Utils.encode(fileBytes)
    }

    // Verifica se um arquivo existe
    fun fileExists(fileName: String): Boolean {
        val filePath = "$storageLocation/$fileName"
        return FileProvider.fileExists(filePath)
    }

    // Deleta um arquivo
    fun deleteFile(fileName: String): Boolean {
        val filePath = "$storageLocation/$fileName"
        return FileProvider.deleteFile(filePath)
    }

    // Cria diretórios se necessário (útil para preparar a estrutura de pastas)
    fun createDirectoriesIfNotExist() {
        FileProvider.createDirectories(storageLocation)
    }

    // Retorna o caminho completo de um arquivo
    fun getFilePath(fileName: String): Path {
        return Paths.get("$storageLocation/$fileName").normalize()
    }

    // Lê um arquivo e retorna o conteúdo como array de bytes
    fun readFileAsBytes(fileName: String): ByteArray {
        val filePath = "$storageLocation/$fileName"
        return FileProvider.readBytesFromFile(filePath)
    }
}