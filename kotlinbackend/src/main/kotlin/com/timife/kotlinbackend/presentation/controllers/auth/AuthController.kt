package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.requests.AuthRequest
import com.timife.kotlinbackend.domain.requests.UserRequest
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.presentation.utils.toAdminUser
import com.timife.kotlinbackend.presentation.utils.toUserModel
import com.timife.kotlinbackend.services.AuthService
import com.timife.kotlinbackend.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
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

    fun registerAdmin(@RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = "Password must be with at least 6 characters",null)

        if(userRequest.password.length < 6) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.copy(message = "Password must have at lease 6 characters"))
        }
        if(!userRequest.email.isValidEmail()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.copy(message = "Please enter a valid email address"))
        }
        return try {
            ResponseEntity.ok(userService.createUser(userRequest.toAdminUser()))
        } catch (e: Exception) {
            ResponseEntity.ok(error.copy(message = e.localizedMessage))
        }catch (e: BadCredentialsException){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        }catch (e: CredentialsExpiredException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    //General Login for all users
    @PostMapping("/login")
    fun logIn(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(authService.authenticate(authRequest))
        } catch (e: BadCredentialsException) {
            val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = "Invalid email or password", null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.localizedMessage)
        }
    }

    @PostMapping("/logout")
    fun logOut(){
        //TODO: logout feature
    }
}

fun String.isValidEmail(): Boolean{
    return this.endsWith("gmail.com") || this.endsWith("yahoo.com")
}

