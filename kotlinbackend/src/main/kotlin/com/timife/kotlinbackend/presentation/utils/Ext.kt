package com.timife.kotlinbackend.presentation.utils

import com.timife.kotlinbackend.domain.Role
import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.dtos.BookDto
import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.requests.UserRequest
import java.util.*

fun UserRequest.toUserModel(): User {
    return User(
        id = null,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        role = Role.USER
    )
}

fun UserRequest.toAdminUser(): User {
    return User(
        id = null,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        role = Role.ADMIN
    )
}

fun BookEntity.toBookDto(): BookDto {
    return BookDto(

        title = this.title,
        author = this.author,
        isbn = this.isbn,
        dateIssued = this.issueDate,
        id = this.id
    )
}

fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        title = this.title,
        author = this.author,
        issueDate = dateIssued,
        isIssued = false,
        isbn = this.isbn,
        id = this.id
    )
}