package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.CheckedOutBook

interface BooksService {
    fun getAllBooks(): List<BookEntity>

    fun createBook(bookEntity: BookEntity): BookEntity

    fun updateBook(bookEntity: BookEntity): BookEntity

    fun viewBook(isBn: String): BookEntity

    fun getCheckedOutBooks(): List<CheckedOutBook>

    fun getIssuedBooks(): List<BookEntity>

    fun issueBook(isBn: String): BookEntity


}