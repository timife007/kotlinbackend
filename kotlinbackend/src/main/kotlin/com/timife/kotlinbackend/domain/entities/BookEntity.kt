package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import java.util.Date

@Entity
@Table(name = "books")
data class BookEntity(
    @Id
    @GeneratedValue
    val id: Int,
    val isbn: Long,
    val title: String,
    val author: String,
    val issueDate: Date? = null,
    val isIssued: Boolean = false
)

//NOTE: So, each item even with similar isbn can have different ids to differentiate them
//when borrowing and should have an issue date.