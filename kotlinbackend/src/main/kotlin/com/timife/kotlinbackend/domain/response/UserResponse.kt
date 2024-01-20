package com.timife.kotlinbackend.domain.response

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor



data class UserResponse(
    val email:String,
    val isSuccessful: Boolean,
    val message: String
)
