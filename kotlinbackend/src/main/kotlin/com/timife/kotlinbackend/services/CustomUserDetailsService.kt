package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.timife.kotlinbackend.domain.User

@Service
class CustomUserDetailsService(private val repository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return repository.findByEmail(username)?.toUserDetails() ?: throw UsernameNotFoundException("User not found")
    }

    private fun ApplicationUser.toUserDetails(): UserDetails {
        return User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()
    }
}