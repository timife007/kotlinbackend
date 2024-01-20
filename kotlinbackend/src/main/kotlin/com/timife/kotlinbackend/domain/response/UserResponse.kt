package com.timife.kotlinbackend.domain.response

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID


data class UserResponse(
    val id: UUID,
    val email: String,
    val isSuccessful: Boolean,
    val message: String? = null
)
