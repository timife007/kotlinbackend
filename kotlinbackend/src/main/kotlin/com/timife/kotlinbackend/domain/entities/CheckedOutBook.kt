package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "checked_out")
data class CheckedOutBook(
    @Id
    val isbn: String,
    val author: String,
    val quantity: Int,
    val title: String
)
