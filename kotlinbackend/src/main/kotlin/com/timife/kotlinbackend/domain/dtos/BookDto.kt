package com.timife.kotlinbackend.domain.dtos

data class BookDto(
    val id: Long,
    val title:String,
    val author: String,
    val isbn: String,
    val dateIssued: String,
)