package com.timife.kotlinbackend.domain

import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Generated
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Builder
@Entity
@Table(name = "_users")
@NoArgsConstructor
@AllArgsConstructor
data class User(
    @Id
    @Generated
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,

    @Enumerated
    val role: Role
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority((role.name)))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}