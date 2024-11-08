//import org.springframework.context.annotation.Bean
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//@EnableWebSecurity
//class SecurityConfig {
//
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .authorizeHttpRequests { requests ->
//                requests
//                    .requestMatchers("/auth/login").permitAll() // Permitir acesso ao login
//                    .anyRequest().authenticated() // Requer autenticação para outros endpoints
//            }
//
//        return http.build()
//    }
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { it.disable() } // Desabilita proteção CSRF, já que usamos tokens
//            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // Sem sessões, o estado é mantido no token
//            .authorizeHttpRequests { auth ->
//                auth
//                    .requestMatchers("/**").permitAll() // Permitir acesso público apenas à rota de login
//                    .anyRequest().authenticated() // Qualquer outra rota precisa de autenticação
//            }
//            .addFilterBefore(
//                BearerTokenFilter(),
//                UsernamePasswordAuthenticationFilter::class.java
//            ) // Filtro para autenticar o token
//
//        return http.build()
//    }
//}
