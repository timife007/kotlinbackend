package com.timife.kotlinbackend.configs

import com.timife.kotlinbackend.repositories.UserRepository
import com.timife.kotlinbackend.security.JwtService
import com.timife.kotlinbackend.services.AuthService
import com.timife.kotlinbackend.services.AuthServiceImpl
//import com.timife.kotlinbackend.services.AuthServiceImpl
//import com.timife.kotlinbackend.security.JwtService
//import com.timife.kotlinbackend.services.AuthService
//import com.timife.kotlinbackend.services.AuthServiceImpl
import com.timife.kotlinbackend.services.CustomUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class ApplicationConfig {

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService {
        return CustomUserDetailsService(userRepository)
    }

    @Bean
    fun getAuthService(
        userRepository: UserRepository,
        userDetailsService: UserDetailsService,
        jwtService: JwtService,
        authenticationManager: AuthenticationManager
    ): AuthService {
        return AuthServiceImpl(
            userDetailService = userDetailsService,
            passwordEncoder = passwordEncoder(),
            jwtService = jwtService,
            authManager = authenticationManager,
            userRepository = userRepository
        )
    }

    @Bean
    fun authenticationProvider(userRepository: UserRepository): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService(userRepository))
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}