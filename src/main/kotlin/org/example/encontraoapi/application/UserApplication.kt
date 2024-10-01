package org.example.encontraoapi.application

import org.example.encontraoapi.dto.User.UserDTO
import org.example.encontraoapi.entity.User
import org.example.encontraoapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserApplication @Autowired constructor(
    private val userService: UserService
) {

    fun getAll(): List<User> {
        return userService.getAll()
    }

    fun getById(id: Long): User? {
        return userService.getById(id)
    }

    fun getByDocument(cpf: String): User? {
        return userService.getByDocument(cpf)
    }

    fun create(data: UserDTO): User {
        try {
            if (data.cpf.isNullOrEmpty()) {
                throw Exception("cpf can not be empty")
            }
            return userService.create(data)
        } catch (e: Exception) {
            throw e
        }
    }

    fun update(id: Long, data: UserDTO): User? {
        return userService.update(id, data)
    }

    fun delete(id: Long): Boolean {
        return userService.delete(id)
    }
}
