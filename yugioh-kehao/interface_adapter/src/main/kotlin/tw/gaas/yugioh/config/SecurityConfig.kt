package tw.gaas.yugioh.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.User.UserBuilder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@Configuration
@EnableWebSecurity
@EnableWebMvc
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .requestMatchers("/api/ping").permitAll()
            .requestMatchers("/api/**").hasAuthority("ROLE_USER")
            .and().httpBasic()
            .and().csrf().disable()
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val users: UserBuilder = User.builder()
        val yuki: UserDetails = users
            .username("yugi")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build()
        val kaiba: UserDetails = users
            .username("kaiba")
            .password(passwordEncoder().encode("password"))
            .roles("USER", "ADMIN")
            .build()
        return InMemoryUserDetailsManager(yuki, kaiba)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
