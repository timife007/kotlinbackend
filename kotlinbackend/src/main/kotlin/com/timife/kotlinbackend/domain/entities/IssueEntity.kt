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
@Table(name = "issues")
@NoArgsConstructor
@AllArgsConstructor
data class IssueEntity(
    @Id
    @GeneratedValue
    val isbn: Long,
    val date: String
)
