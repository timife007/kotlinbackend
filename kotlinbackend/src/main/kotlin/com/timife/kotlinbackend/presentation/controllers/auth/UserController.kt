package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.ClearResponse
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.presentation.utils.toUserModel
import com.timife.kotlinbackend.services.UserService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping("/register")
    fun addUser(@RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = "Error authenticating user", null)

        if (!userRequest.email.isValidEmail()) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(error.copy(message = "Please enter a valid email address"))
        } else if (userRequest.password.length < 6) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(error.copy(message = "Password must have at lease 6 characters"))
        }
        return try {
            ResponseEntity.ok(userService.createUser(userRequest.toUserModel()))
        } catch (e: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        } catch (e: CredentialsExpiredException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
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

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int): ResponseEntity<Any> {
        if (!userService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClearResponse(message = "User not found"))
        }
        return try {
            userService.deleteUser(id)
            ResponseEntity.ok(ClearResponse(message = "User deleted successfully"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.localizedMessage)
        }
    }

    @DeleteMapping
    fun deleteAllUsers(): ResponseEntity<Any> {
        return try {
            userService.clearUsers()
            ResponseEntity.ok(ClearResponse(message = "users db successfully cleared"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.localizedMessage)
        }
    }
}