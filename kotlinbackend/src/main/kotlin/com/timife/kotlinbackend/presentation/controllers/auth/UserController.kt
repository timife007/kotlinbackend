package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.presentation.utils.toUserModel
import com.timife.kotlinbackend.services.AuthService
import com.timife.kotlinbackend.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping("/register")
    fun addUser(@RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.createUser(userRequest.toUserModel()))
        } catch (e: Exception) {
            val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = e.localizedMessage, null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.getAllUsers())
        } catch (e: Exception) {
            val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = e.localizedMessage, null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }
    }
}