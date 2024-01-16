package com.timife.kotlinbackend.controllers

import com.timife.kotlinbackend.domain.Role
import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.repositories.UserRepository
import com.timife.kotlinbackend.security.JwtService
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authManager: AuthenticationManager
) : AuthService {
    override fun register(request: UserRequest): UserResponse? {
        val user = User(
            id = UUID.randomUUID(),
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = request.password,
            role = Role.ADMIN
        )
        return if (userRepository.findByEmail(user.email) == null) {
            userRepository.save(user)
            UserResponse(email = user.email, isSuccessful = true)
        } else {
            null
        }
    }

    override fun authenticate(authRequest: AuthRequest): AuthResponse? {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        val user = userRepository.findByEmail(authRequest.email)
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