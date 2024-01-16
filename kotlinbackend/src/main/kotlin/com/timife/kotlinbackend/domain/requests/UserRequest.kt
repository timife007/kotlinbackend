package com.timife.kotlinbackend.domain.requests

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


data class UserRequest(
    val firstName:String,
    val lastName: String,
    val email: String,
    val password: String
)
