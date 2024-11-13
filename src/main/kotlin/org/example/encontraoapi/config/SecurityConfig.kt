//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
//
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig : WebSecurityConfiguration() {
//    @Bean
//    fun bearerTokenFilter(): BearerTokenFilter = BearerTokenFilter()
//
//    fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .requestMatchers("/public/**").permitAll() // Public endpoints
//            .anyRequest().authenticated() // Secure all other endpoints
//            .and()
//            .addFilterBefore(BearerTokenFilter(), BearerTokenFilter::class.java)
//    }
//}