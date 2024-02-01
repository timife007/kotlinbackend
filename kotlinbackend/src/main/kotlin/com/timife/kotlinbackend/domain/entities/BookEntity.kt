package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "books")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val isbn: String,
    val title: String,
    val author: String,
    val issueDate: Date? = null,
    val isIssued: Boolean = false
)

//NOTE: So, each item even with similar isbn can have different ids to differentiate them
//when borrowing and should have an issue date.