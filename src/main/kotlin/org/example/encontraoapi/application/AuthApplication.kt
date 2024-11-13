package org.example.encontraoapi.application

import org.example.encontraoapi.dto.Auth.CredentialsDTO
import org.example.encontraoapi.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthApplication @Autowired constructor(
    private val authService: TokenService
) {
    fun login(credentialsDTO: CredentialsDTO): String {
        return authService.login(credentialsDTO.cpf, credentialsDTO.password)
    }
}
