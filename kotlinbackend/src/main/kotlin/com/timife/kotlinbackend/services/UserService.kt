package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.response.UserResponse

interface UserService {

    fun createUser(user: User): UserResponse?

    fun getAllUsers(): List<UserResponse>

    fun existsById(id: Int): Boolean

    fun clearUsers()

    fun deleteUser(id:Int)
}