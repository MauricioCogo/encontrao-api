//import org.example.encontraoapi.service.AuthService
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.AuthenticationException
//import org.springframework.stereotype.Component
//
//@Component
//class AuthManager(private val authService: AuthService) : AuthenticationManager {
//
//    @Throws(AuthenticationException::class)
//    override fun authenticate(authentication: Authentication): AuthenticationToken {
//
//        if (authentication is AuthenticationToken) {
//
//                        val login = authentication.name
//            val password = authentication.credentials.toString()
//            val token = authService.loginWithCredentials(login, password).first
//
//            return AuthenticationToken(token)
//        } else {
//            throw InvalidUsernameOrPassword()
//        }
//
//    }
//
//}