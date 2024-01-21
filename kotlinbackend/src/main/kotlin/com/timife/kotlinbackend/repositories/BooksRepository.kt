package com.timife.kotlinbackend.repositories

import com.timife.kotlinbackend.domain.entities.BookEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BooksRepository : CrudRepository<BookEntity, UUID> {
    fun getIssuedBooks(): MutableIterable<BookEntity> {
        return findAll().filter { it.isIssued }.toMutableList()
    }

    @Query("SELECT * FROM books WHERE ISBN = ?1")
    fun findBooksWithisbnEqualTo(isbn: String): BookEntity
}