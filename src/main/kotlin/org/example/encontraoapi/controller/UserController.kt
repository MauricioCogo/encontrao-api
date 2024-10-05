package org.example.encontraoapi.controller

import org.example.encontraoapi.application.UserApplication
import org.example.encontraoapi.dto.User.UserDTO
import org.example.encontraoapi.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
    private val userApplication: UserApplication
) {
    @GetMapping
    fun getAllUsers(): List<User> {
        return userApplication.getAll()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userApplication.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @GetMapping("cpf/{cpf}")
    fun getUserByDocument(@PathVariable cpf: String): ResponseEntity<UserDTO> {
        val user = userApplication.getByDocument(cpf) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody data: UserDTO): ResponseEntity<User> {
        val createdUser = userApplication.create(data)
        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody data: UserDTO
    ): ResponseEntity<User> {
        val updatedUser = userApplication.update(id, data) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userApplication.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
