package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.domain.response.UserResponse

interface AuthService {
    fun authenticate(authRequest: AuthRequest): AuthResponse?

    fun logout()
}