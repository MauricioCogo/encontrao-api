//import io.jsonwebtoken.Jwts
//import org.springframework.security.oauth2.jose.jws.JwsAlgorithms
//import java.nio.charset.StandardCharsets
//import java.util.*
//import javax.crypto.spec.SecretKeySpec
//
//
//object TokenProvider {
//    private const val SECRET_KEY = "your_secret_key"
//
//    // Generate JWT Token
//    fun generateToken(username: String): String {
//        // Convert the secret key into a SecretKeySpec
//        val keyBytes = SECRET_KEY.toByteArray(StandardCharsets.UTF_8)
//        val key = SecretKeySpec(keyBytes, JwsAlgorithms.ES256)
//
//        return Jwts.builder()
//            .setSubject(username)
//            .setIssuedAt(Date())
//            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
//            .signWith(key) // Use the converted key
//            .compact()
//    }
//
//    // Validate the JWT Token
//    fun validateToken(token: String): Boolean {
//        return try {
//            Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    // Extract username from the JWT Token
//    fun extractUsername(token: String): String {
//        return Jwts.parser()
//            .setSigningKey(SECRET_KEY)
//            .parseClaimsJws(token)
//            .body.subject
//    }
//}