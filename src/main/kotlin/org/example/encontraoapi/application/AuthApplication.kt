//package org.example.encontraoapi.application
//
//import org.example.encontraoapi.dto.Auth.CredentialsDTO
//import org.example.encontraoapi.entity.User
//import org.example.encontraoapi.service.AuthService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//
//@Component
//class AuthApplication @Autowired constructor(
//    private val authService: AuthService
//) {
//    fun login(credentialsDTO: CredentialsDTO): String{
//        if (credentialsDTO.cpf.isNullOrEmpty()){
//            throw IllegalArgumentException("cpf can't be empty")
//        }
//
//        if (credentialsDTO.password.isNullOrEmpty()){
//            throw IllegalArgumentException("password can't be empty")
//        }
//
//        return authService.login(credentialsDTO.cpf, credentialsDTO.password)
//    }
//}
