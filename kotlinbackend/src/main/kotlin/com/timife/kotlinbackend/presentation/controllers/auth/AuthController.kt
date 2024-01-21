package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.presentation.utils.toAdminUser
import com.timife.kotlinbackend.services.AuthService
import com.timife.kotlinbackend.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class AuthController(
    private val authService: AuthService,
    private val userService: UserService
) {

    //Registration for Admin Users
    @PostMapping("/admin/signup")
    fun registerAdmin(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createUser(userRequest.toAdminUser()))
    }

    //General Login for all users
    @PostMapping("/login")
    fun logIn(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(authService.authenticate(authRequest))
        } catch (e: Exception) {
            val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = e.localizedMessage, null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }
    }
}

