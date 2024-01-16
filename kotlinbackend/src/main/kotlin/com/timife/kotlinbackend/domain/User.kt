package com.timife.kotlinbackend.domain

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Generated
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "_users")
data class User(
    @Id
    @GeneratedValue
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,

    @Enumerated
    val role: Role
)