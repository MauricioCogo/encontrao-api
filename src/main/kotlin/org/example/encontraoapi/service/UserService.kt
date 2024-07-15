package org.example.encontraoapi.service

import org.example.encontraoapi.entity.User
import org.example.encontraoapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun createUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, userDetails: User): User? {
        val user = userRepository.findById(id).orElse(null) ?: return null
        val updatedUser = user.copy(
            name = userDetails.name,
            cpf = userDetails.cpf,
            registration = userDetails.registration,
            password = userDetails.password,
            roles = userDetails.roles,
            isEvaluator = userDetails.isEvaluator,
            isAdmin = userDetails.isAdmin,
            campus = userDetails.campus
        )
        return userRepository.save(updatedUser)
    }

    fun deleteUser(id: Long): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
