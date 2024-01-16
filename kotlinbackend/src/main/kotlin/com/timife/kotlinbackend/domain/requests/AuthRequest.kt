package com.timife.kotlinbackend.domain.requests

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


data class AuthRequest(
    val email: String,
    val password: String
)
