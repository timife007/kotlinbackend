package com.timife.kotlinbackend.domain.response

import org.springframework.http.HttpStatus

data class  ErrorResponse(
    val status: HttpStatus,
    val message: String,
    val errorDetails: String? = ""
)