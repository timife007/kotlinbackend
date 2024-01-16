package com.timife.kotlinbackend.repositories

import com.timife.kotlinbackend.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String): User?
}