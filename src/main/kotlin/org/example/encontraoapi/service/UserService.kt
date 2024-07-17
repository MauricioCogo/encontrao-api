package org.example.encontraoapi.service

import org.example.encontraoapi.entity.User
import org.example.encontraoapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<User> {
        try {
            return userRepository.findAll()
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getUserById(id: Long): User? {
        try {
            return userRepository.findById(id).orElse(null)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun createUser(user: User): User {
        try {
            return userRepository.save(user)
        } catch (e: Exception) {
            throw e
        }
    }

    fun updateUser(id: Long, userDetails: User): User? {
        try {
            val user = userRepository.findById(id).orElse(null) ?: return null

            val updatedUser = User().also {
                it.id = user.id
                it.name = userDetails.name
                it.cpf = userDetails.cpf
                it.registration = userDetails.registration
                it.password = userDetails.password
                it.roles = userDetails.roles
                it.isEvaluator = userDetails.isEvaluator
                it.isAdmin = userDetails.isAdmin
                it.campus = userDetails.campus
            }

            return userRepository.save(updatedUser)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun deleteUser(id: Long): Boolean {
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
