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
@Table(name = "issues")
@NoArgsConstructor
@AllArgsConstructor
data class IssueEntity(
    @EmbeddedId
    val isbn: String,
    val date: String
)
