package com.timife.kotlinbackend.domain

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Generated
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "_users")
data class User(
    @Id
    @GeneratedValue
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role
)