package org.example.encontraoapi.controller

import org.example.encontraoapi.application.AuthApplication
import org.example.encontraoapi.dto.Auth.CredentialsDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController @Autowired constructor(
    private val authApplication: AuthApplication
) {
    @PostMapping("/login")
    fun login(@RequestBody credentialsDTO: CredentialsDTO): ResponseEntity<String> {
        try {
            val token = authApplication.login(credentialsDTO)
            return ResponseEntity.ok(token)  // Retorna oquan token gerado
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(e.message)  // Erro de login
        }
    }
}
