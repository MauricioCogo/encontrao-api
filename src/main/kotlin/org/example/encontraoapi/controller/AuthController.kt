//package org.example.encontraoapi.controller
//
//import org.example.encontraoapi.application.AuthApplication
//import org.example.encontraoapi.dto.Auth.CredentialsDTO
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/auth")
//class AuthController @Autowired constructor(
//    private val authApplication: AuthApplication
//) {
//    @PostMapping("/login")
//    fun getUserByDocument(@RequestBody credentialsDTO: CredentialsDTO): ResponseEntity<String> {
//        val user = authApplication.login(credentialsDTO) ?: return ResponseEntity.notFound().build()
//        return ResponseEntity.ok(user)
//    }
//}
