package org.example.encontraoapi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService @Autowired constructor(
    private val userService: UserService,
    private val tokenService: TokenService
) {
    private val SECRET_KEY = "your_secret_key" // Chave secreta
    private val EXPIRATION_TIME = 86400000 // 1 dia em milissegundos

    fun login(cpf: String, password: String): String {
        try {
            var user = userService.getByDocument(cpf)

            if (user == null) {
                throw IllegalArgumentException("User does not exist")
            }

            if (user.password != password) {
                throw IllegalArgumentException("Invalid password")
            }

            val token = tokenService.generateToken(user, null)

            return token
        } catch (ex: Exception) {
            throw ex
        }
    }
}
