package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "books")
data class BookEntity(
    @Id
    val isbn: String,
    val title: String,
    val quantity: Int,
    val edition: String,
    val author: String,
)