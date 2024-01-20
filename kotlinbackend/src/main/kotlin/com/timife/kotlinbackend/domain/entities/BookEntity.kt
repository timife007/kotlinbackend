package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor

@Builder
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
data class BookEntity(
    @Id
    @GeneratedValue
    val isbn: Long,
    val title: String,
    val author: String
)