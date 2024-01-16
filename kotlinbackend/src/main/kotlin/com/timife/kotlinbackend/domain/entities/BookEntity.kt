package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id

@Builder
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
data class BookEntity(
    @EmbeddedId
    val isbn: String,
    val title: String,
    val author: String
)