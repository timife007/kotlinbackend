package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.services.AuthService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/auth")
class AuthController (
        private val authService: AuthService
){
}