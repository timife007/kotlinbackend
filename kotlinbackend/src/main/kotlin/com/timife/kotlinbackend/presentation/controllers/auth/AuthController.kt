package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.Role
import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.AuthResponse
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.services.AuthService
import org.springframework.http.HttpStatus
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
    fun addUser(@RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        val userEntity = authService.register(userRequest.toUserModel())
        return ResponseEntity(userEntity, HttpStatus.CREATED)
    }

    @PostMapping("/admin/signup")
    fun registerAdmin(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(authService.register(userRequest.toAdminUser()))
    }

    @PostMapping("/login")
    fun logIn(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(authService.authenticate(authRequest))
        }catch (e: Exception){

            val error  = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = e.localizedMessage,null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }
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

data class  ErrorResponse(
    val status: HttpStatus,
    val message: String,
    val errorDetails: String?
)