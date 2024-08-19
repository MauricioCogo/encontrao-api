//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import jakarta.servlet.Filter
//import jakarta.servlet.FilterChain
//import jakarta.servlet.FilterConfig
//import jakarta.servlet.ServletRequest
//import jakarta.servlet.ServletResponse
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.stereotype.Component
//
//@Component
//class BearerTokenFilter : Filter {
//
//    override fun doFilter(
//        request: ServletRequest,
//        response: ServletResponse,
//        chain: FilterChain
//    ) {
//        // Converta para HttpServletRequest e HttpServletResponse
//        val httpRequest = request as HttpServletRequest
//        val httpResponse = response as HttpServletResponse
//
//        // Obtenha o token da requisição
//        val token = getTokenFromRequest(httpRequest)
//
//        if (token != null && validateToken(token)) {
//            val userDetails: UserDetails = getUserFromToken(token) // Obtenha detalhes do usuário a partir do token
//            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//            SecurityContextHolder.getContext().authentication = authentication // Defina a autenticação no contexto de segurança
//        }
//
//        // Continue a cadeia de filtros
//        chain.doFilter(httpRequest, httpResponse)
//    }
//
//    private fun getTokenFromRequest(request: HttpServletRequest): String? {
//        val bearerToken = request.getHeader("Authorization")
//        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            bearerToken.substring(7) // Remova o prefixo "Bearer "
//        } else null
//    }
//
//    private fun validateToken(token: String): Boolean {
//        // Lógica para validar o token (JWT, por exemplo)
//        return true // Simulação de validação
//    }
//
//    private fun getUserFromToken(token: String): UserDetails {
//        // Extraia e retorne o UserDetails a partir do token
//        return org.springframework.security.core.userdetails.User.withUsername("user")
//            .password("")
//            .authorities(emptyList())
//            .build()
//    }
//
//    override fun init(filterConfig: FilterConfig) {}
//    override fun destroy() {}
//}
