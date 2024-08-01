package org.example.encontraoapi.application

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

    fun getById(id: Long): User?{
        return userService.getById(id)
    }

    fun create(user: User): User {
        try {
            if(user.cpf.isNullOrEmpty()){
                throw Exception("cpf can not be empty")
            }
            return userService.create(user)
        }catch (e: Exception){
            throw e
        }
    }

    fun update(id: Long, userDetails: User): User? {
        return userService.update(id, userDetails)
    }

    fun delete(id: Long): Boolean {
        return userService.delete(id)
    }
}
