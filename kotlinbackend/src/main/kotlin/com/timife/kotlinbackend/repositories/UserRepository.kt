package com.timife.kotlinbackend.repositories

import com.timife.kotlinbackend.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): Optional<User?>?
}