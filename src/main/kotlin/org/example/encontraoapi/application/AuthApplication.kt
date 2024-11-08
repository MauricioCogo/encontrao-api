//package org.example.encontraoapi.application
//
//import org.example.encontraoapi.dto.Auth.CredentialsDTO
//import org.example.encontraoapi.service.AuthService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//
//@Component
//class AuthApplication @Autowired constructor(
//    private val authService: AuthService
//) {
//    fun login(credentialsDTO: CredentialsDTO): String {
//        // Aqui não precisa mais verificar CPF ou senha porque isso já é feito no AuthService.
//        return authService.login(credentialsDTO.cpf, credentialsDTO.password)
//    }
//}
