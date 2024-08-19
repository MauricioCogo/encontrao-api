package org.example.encontraoapi.service

import org.example.encontraoapi.dto.User.UserDTO
import org.example.encontraoapi.entity.Campus
import org.example.encontraoapi.entity.User
import org.example.encontraoapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class  UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val fileService: FileService,
) {
    fun getAll(): List<User> {
        try {
            return userRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getById(id: Long): User? {
        try {
            return userRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun create(data: UserDTO): User {
        try {
            val user = data.toEntity()
            return userRepository.save(user)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, data: UserDTO): User? {
        try {
            val user = userRepository.findById(id).orElse(null) ?: return null

            if (data.avatar != null && data.avatar!!.isNotBlank()) {
                val fileName = "${UUID.randomUUID()}.png"
                val filePath = fileService.saveBase64File(data.avatar!!, fileName)
                user.avatar = filePath
            }

            val updatedUser = User().also {
                it.id = user.id
                it.name = data.name
                it.cpf = data.cpf
                it.registration = data.registration
                it.password = data.password
                it.roles = data.roles
                it.isEvaluator = data.isEvaluator ?: false
                it.isAdmin = data.isAdmin ?: false
                it.campusId = data.campusId
            }

            return userRepository.save(updatedUser)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun delete(id: Long): Boolean {
        try {
            var user = userRepository.findById(id).orElse(null)

            user.updated_by = 1
            user.updated = LocalDateTime.now()
            user.deleted = true

            userRepository.save(user)
            return true
        } catch (ex: Exception) {
            return false
        }

    }
}
