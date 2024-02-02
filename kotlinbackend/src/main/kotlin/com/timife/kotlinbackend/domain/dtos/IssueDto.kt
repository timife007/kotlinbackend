package com.timife.kotlinbackend.domain.dtos

data class IssueDto(
    val person: String,
    val isbn: String,
    val quantity: Int
)