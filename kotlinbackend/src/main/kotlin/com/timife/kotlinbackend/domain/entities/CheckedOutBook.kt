package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "checked_out")
data class CheckedOutBook(
    val id: Int,
    val isbn: String,
    val author: String,
    val quantity: Int,
    val title: String
)
