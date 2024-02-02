package com.timife.kotlinbackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "issue")
data class IssueEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String? = null,
    val isbn: String,
    val title: String,
    val issueDate: LocalDateTime,
    val author:String,
    val person: String? = null
)