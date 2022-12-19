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


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .requestMatchers("/api/ping").permitAll()
            .requestMatchers("/api/**").hasAuthority("USER")
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
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
