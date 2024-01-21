package com.timife.kotlinbackend.services.impl

import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.repositories.UserRepository
import com.timife.kotlinbackend.security.JwtService
import com.timife.kotlinbackend.services.AuthService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userDetailService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authManager: AuthenticationManager,
) : AuthService {

    override fun authenticate(authRequest: AuthRequest): AuthResponse? {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        val user = userDetailService.loadUserByUsername(authRequest.email)
        val accessToken = user?.let {
            jwtService.generateToken(it)
        }
        return if (accessToken != null) {
            AuthResponse(accessToken)
        } else {
            null
        }
    }
}