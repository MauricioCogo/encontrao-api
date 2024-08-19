//package org.example.encontraoapi.service
//
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.SignatureAlgorithm
//import org.example.encontraoapi.entity.User
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import java.util.*
//
//@Service
//class AuthService @Autowired constructor(
//    private val userService: UserService,
//) {
//    private val SECRET_KEY = "your_secret_key" // Chave secreta
//    private val EXPIRATION_TIME = 86400000 // 1 dia em milissegundos
//
//    fun login(cpf: String, password: String): String {
//        try {
//            var user = userService.getByDocument(cpf)
//
//            if (user == null) {
//                throw IllegalArgumentException("user does exist")
//            }
//
//            if (user.password != password) {
//                throw IllegalArgumentException("password can't be empty")
//            }
//
//            val token = generateToken(user)
//
//            return token
//        } catch (ex: Exception) {
//            throw ex
//        }
//    }
//
//    fun generateToken(user: User): String {
//        return Jwts.builder()
//            .setSubject(user.cpf)
//            .setIssuedAt(Date())
//            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//            .compact()
//    }
//}
