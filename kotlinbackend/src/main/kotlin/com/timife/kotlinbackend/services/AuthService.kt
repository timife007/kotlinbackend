package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.domain.response.UserResponse

interface AuthService {
    fun register(request: User): UserResponse?

    fun authenticate(authRequest: AuthRequest): AuthResponse?
}