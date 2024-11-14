import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.encontraoapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class SecurityFilter @Autowired constructor(
    private val tokenService: org.example.encontraoapi.service.TokenService,
    private val userService: UserService
) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = extractToken(request)

        if (token != null) {
            val subject: String = tokenService.validateToken(token)
            val userDetails: org.example.encontraoapi.entity.User? = userService.getByDocument(subject)

            val authentication =
                UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails?.getAuthorities()
                )
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null

        return authHeader.replace("Bearer ", "")
    }
}