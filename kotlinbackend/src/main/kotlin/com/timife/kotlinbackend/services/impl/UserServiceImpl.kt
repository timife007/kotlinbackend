package com.timife.kotlinbackend.services.impl

import com.timife.kotlinbackend.domain.User
import com.timife.kotlinbackend.domain.response.UserResponse
import com.timife.kotlinbackend.repositories.UserRepository
import com.timife.kotlinbackend.services.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun createUser(user: User): UserResponse? {
        val updatedUser = user.copy(password = passwordEncoder.encode(user.password))
        return if (userRepository.findByEmail(updatedUser.email) == null) {
            userRepository.save(updatedUser)
            UserResponse(id = user.id, email = user.email, isSuccessful = true, "User successfully registered")
        } else {
            UserResponse(id = user.id, email = user.email, isSuccessful = false, "User already registered")
        }
    }

    override fun getAllUsers(): List<UserResponse> {
        val users = userRepository.findAll().toList()
        return users.map {
            UserResponse(it.id, it.email, true)
        }
    }


}