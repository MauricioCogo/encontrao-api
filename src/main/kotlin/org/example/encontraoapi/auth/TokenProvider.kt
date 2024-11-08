//import com.auth0.jwt.JWT
//import com.auth0.jwt.algorithms.Algorithm
//import com.auth0.jwt.exceptions.JWTCreationException
//import com.auth0.jwt.exceptions.JWTVerificationException
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//import java.time.Instant
//import java.time.LocalDateTime
//import java.time.ZoneOffset
//
//@Service
//class TokenProvider {
//
//    @Value("\${security.jwt.token.secret-key}")
//    private lateinit var jwtSecret: String  // O segredo da chave é injetado da configuração
//
//    // Gera o token de acesso
//    fun generateAccessToken(user: User): String {
//        try {
//            val algorithm = Algorithm.HMAC256(jwtSecret)
//            return JWT.create()
//                .withSubject(user.username)
//                .withClaim("username", user.username)
//                .withExpiresAt(genAccessExpirationDate())  // Define o tempo de expiração
//                .sign(algorithm)  // Assina o token com o algoritmo HMAC256 e a chave secreta
//        } catch (exception: JWTCreationException) {
//            throw JWTCreationException("Error while generating token", exception)
//        }
//    }
//
//    // Valida o token recebido
//    fun validateToken(token: String): String {
//        try {
//            val algorithm = Algorithm.HMAC256(jwtSecret)
//            return JWT.require(algorithm)
//                .build()
//                .verify(token)
//                .subject  // Retorna o "subject" (aqui, o username)
//        } catch (exception: JWTVerificationException) {
//            throw JWTVerificationException("Error while validating token", exception)
//        }
//    }
//
//    // Gera a data de expiração para o token (2 horas de validade)
//    private fun genAccessExpirationDate(): Instant {
//        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
//    }
//}
