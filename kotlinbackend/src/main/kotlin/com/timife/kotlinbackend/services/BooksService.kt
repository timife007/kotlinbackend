package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.CheckedOutBook
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BooksService {
    fun getAllBooks(pageable: Pageable): Page<BookEntity>

    fun createBook(bookEntity: BookEntity): BookEntity

    fun updateBook(bookEntity: BookEntity): BookEntity

    fun viewBook(isBn: String): BookEntity

    fun getCheckedOutBooks(): List<CheckedOutBook>

    fun getIssuedBooks(): List<BookEntity>

    fun issueBook(isBn: String): BookEntity
    fun bookExists(isbn: String): Boolean

    fun getUnissuedBook(isBn: String): BookEntity
}