package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.presentation.utils.toUserModel
import com.timife.kotlinbackend.services.UserService
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
        return try {
            ResponseEntity.ok(userService.createUser(userRequest.toUserModel()))
        } catch (e: BadCredentialsException){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        }catch (e: CredentialsExpiredException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }catch (e: Exception){
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
}