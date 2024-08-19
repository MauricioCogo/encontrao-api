//import com.fasterxml.jackson.core.JsonProcessingException
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.SignatureAlgorithm
//import io.jsonwebtoken.security.Keys
//import org.springframework.stereotype.Component
//import java.io.IOException
//import java.time.Instant
//import java.util.concurrent.TimeUnit
//
//@Component
//class TokenProvider {
//
//    companion object {
//        /* secret 64 characters required (512bits) */
//        private const val SECRET = "ackN#fnb#%lNN8gANZNJ^OJI&Za!bhyChJJq2JD1ZV9Z4gZZn@#Kx8CzJJ4Q6lh!"
//        /* expiration in hours */
//        private const val EXPIRATION = 12L
//    }
//
//    private val key = Keys.hmacShaKeyFor(SECRET.toByteArray())
//
//    private val mapper = jacksonObjectMapper()
//
//    @Throws(JsonProcessingException::class)
//    fun encode(jwtObj: Token): String {
//        val iat = Instant.now().epochSecond
//        val exp = iat + TimeUnit.HOURS.toSeconds(EXPIRATION)
//
//        jwtObj.also {
//            it.iat = iat
//            it.exp = exp
//        }
//
//        val payload = mapper.writeValueAsString(jwtObj)
//
//        return Jwts.builder()
//            .setPayload(payload)
//            .signWith(key, SignatureAlgorithm.HS512)
//            .compact()
//    }
//
//    @Throws(IOException::class)
//    fun decode(jwtString: String): Token {
//
//        val payload = Jwts.parserBuilder()
//            .setSigningKey(key)
//            .build()
//            .parse(jwtString)
//            .body
//
//        val json = mapper.writeValueAsString(payload)
//
//        return mapper.readValue(json, Token::class.java)
//    }
//
//    fun refreshToken(headerAuth: String): String {
//        val authentication = getAuthentication(headerAuth)
//
//        val iat = Instant.now().epochSecond
//        val exp = iat + TimeUnit.HOURS.toMillis(EXPIRATION)
//
//        val newToken = authentication?.getToken()!!
//
//        newToken.also {
//            it.iat = iat
//            it.exp = exp
//        }
//
//        return encode(newToken)
//    }
//
//    fun isTokenValid(headerAuth: String): Boolean {
//        val authentication = getAuthentication(headerAuth)
//
//        val token = authentication?.getToken()
//
//        if (token != null) {
//            val userId = token.sub
//
//            val expirationDate = token.exp ?: 0
//            val now = Instant.now().epochSecond
//
//            if (userId != null && expirationDate >= now) {
//                return true
//            }
//        }
//        return false
//    }
//
//    fun getAuthentication(headerAuth: String): AuthenticationToken? {
//        return try {
//            val token = headerAuth.replace("Bearer ", "")
//
//            val principal = decode(token)
//            AuthenticationToken(principal)
//
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//}