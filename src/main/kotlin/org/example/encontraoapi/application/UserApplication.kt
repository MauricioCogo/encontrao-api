package org.example.encontraoapi.application

import org.example.encontraoapi.entity.User
import org.example.encontraoapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserApplication @Autowired constructor(
    private val userService: UserService
) {

    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    fun getUserById(id: Long): User?{
        return userService.getUserById(id)
    }

    fun createUser(user: User): User {
        try {
            if(user.cpf.isNullOrEmpty()){
                throw Exception("cpf can not be empty")
            }
            return userService.createUser(user)
        }catch (e: Exception){
            throw e
        }
    }

    fun updateUser(id: Long, userDetails: User): User? {
        return userService.updateUser(id, userDetails)
    }

    fun deleteUser(id: Long): Boolean {
        return userService.deleteUser(id)
    }
}
