package org.example.encontraoapi.service

import com.auth0.jwt.algorithms.Algorithm
import org.example.encontraoapi.entity.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class TokenService {
    @Value("\${api.security.token.secret}")
    private val secret: String? = null


    /**
     * Generate a new token.
     */
    fun generateToken(userDetails: User, additionalData: Map<String?, Any?>?): String {
        val algorithm: Algorithm = Algorithm.HMAC256(secret)

        return com.auth0.jwt.JWT.create()
            .withIssuer("app-personal")
            .withSubject(userDetails.name)
            .withPayload(additionalData)
            .withExpiresAt(Date.from(generateExpirationDate()))
            .sign(algorithm)
    }


    /**
     * Generate expirationToke.
     */
    private fun generateExpirationDate(): Instant {
        return LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"))
    }


    /**
     * validate Token.
     */
    fun validateToken(token: String?): String {
        val algorithm: Algorithm = Algorithm.HMAC256(secret)
        return com.auth0.jwt.JWT.require(algorithm)
            .withIssuer("app-personal")
            .build()
            .verify(token)
            .getSubject()
    }
}