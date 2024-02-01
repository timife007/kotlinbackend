package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "checked_out")
data class CheckedOutBook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val isbn: String,
    val author: String,
    val quantity: Int,
    val title: String
)
