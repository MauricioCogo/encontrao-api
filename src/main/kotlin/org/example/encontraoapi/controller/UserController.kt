package org.example.encontraoapi.controller

import org.example.encontraoapi.application.UserApplication
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
    fun getAllUsers(): List<User> = userApplication.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userApplication.getUserById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userApplication.createUser(user)
        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userDetails: User
    ): ResponseEntity<User> {
        val updatedUser = userApplication.updateUser(id, userDetails) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userApplication.deleteUser(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
