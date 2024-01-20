package com.timife.kotlinbackend.presentation.utils

import com.timife.kotlinbackend.domain.Role
import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.requests.UserRequest
import java.util.*

fun UserRequest.toUserModel(): User {
    return User(
        id = UUID.randomUUID(),
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        role = Role.USER
    )
}

fun UserRequest.toAdminUser(): User {
    return User(
        id = UUID.randomUUID(),
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        role = Role.ADMIN
    )
}