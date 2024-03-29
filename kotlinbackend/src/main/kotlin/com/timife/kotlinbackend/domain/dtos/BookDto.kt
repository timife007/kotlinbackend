package com.timife.kotlinbackend.domain.dtos

data class BookDto(
    val title: String,
    val author: String,
    val isbn: String,
    val quantity: Int,
    val edition: String
)