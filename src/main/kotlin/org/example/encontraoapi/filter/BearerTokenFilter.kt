//import jakarta.servlet.FilterChain
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.web.filter.OncePerRequestFilter
//
//class BearerTokenFilter : OncePerRequestFilter() {
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val token = extractToken(request)
//        if (token != null && TokenProvider.validateToken(token)) {
//            val username = TokenProvider.extractUsername(token)
//            val authentication = UsernamePasswordAuthenticationToken(username, null, emptyList())
//            SecurityContextHolder.getContext().authentication = authentication
//        }
//        filterChain.doFilter(request, response)
//    }
//
//    private fun extractToken(request: HttpServletRequest): String? {
//        val authHeader = request.getHeader("Authorization")
//        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            authHeader.substring(7)
//        } else {
//            null
//        }
//    }
//}