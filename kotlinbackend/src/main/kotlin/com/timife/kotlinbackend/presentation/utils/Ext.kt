package com.timife.kotlinbackend.presentation.utils

import com.timife.kotlinbackend.domain.Role
import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.dtos.BookDto
import com.timife.kotlinbackend.domain.dtos.IssueDto
import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.IssueEntity
import com.timife.kotlinbackend.domain.requests.UserRequest
import java.time.LocalDateTime
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
        quantity = this.quantity,
        edition = this.edition
    )
}

fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        title = this.title,
        author = this.author,
        isbn = this.isbn,
        edition = this.edition,
        quantity = this.quantity
    )
}


fun BookEntity.toIssueEntity(): IssueEntity{
    return IssueEntity(
        title = this.title,
        isbn = this.isbn,
        author = this.author,
        issueDate = LocalDateTime.now(),
        id = null,
        quantity = this.quantity
    )
}

//fun IssueDto.toIssueEntity():IssueEntity{
//    return IssueEntity(
//        id = null,
//        issueDate = LocalDateTime.now(),
//        isbn = this.isbn,
//        author = this
//    )
//}