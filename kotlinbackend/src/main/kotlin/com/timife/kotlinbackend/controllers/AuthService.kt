package com.timife.kotlinbackend.controllers

import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.domain.response.UserResponse

interface AuthService {
    fun register(request: UserRequest): UserResponse?

    fun authenticate(authRequest: AuthRequest): AuthResponse?
}