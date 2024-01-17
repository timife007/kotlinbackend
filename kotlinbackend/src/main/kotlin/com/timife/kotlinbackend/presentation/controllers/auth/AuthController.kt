package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.Role
import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/user/signup")
    fun register(userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(authService.register(userRequest.toUserModel()))
    }

    @PostMapping("/admin/signup")
    fun registerAdmin(userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(authService.register(userRequest.toAdminUser()))
    }

    @PostMapping("/login")
    fun logIn(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.authenticate(authRequest))
    }

    private fun UserRequest.toUserModel(): User {
        return User(
            id = UUID.randomUUID(),
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password,
            role = Role.USER
        )
    }

    private fun UserRequest.toAdminUser(): User {
        return User(
            id = UUID.randomUUID(),
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password,
            role = Role.ADMIN
        )
    }
}