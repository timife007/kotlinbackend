package com.timife.kotlinbackend.repositories

import com.timife.kotlinbackend.domain.entities.BookEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.awt.print.Book
import java.util.UUID

@Repository
interface BooksRepository : CrudRepository<BookEntity, String>, PagingAndSortingRepository<BookEntity, String> {
//    fun findAllByIsIssuedTrue(): MutableIterable<BookEntity>

//    fun findOneByIsbnIgnoreCase(isbn: String): BookEntity

//    fun findAllByIsIssuedFalse(): MutableIterable<BookEntity>

}