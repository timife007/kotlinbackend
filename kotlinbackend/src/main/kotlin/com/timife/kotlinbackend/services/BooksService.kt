package com.timife.kotlinbackend.services

import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.CheckedOutBook
import com.timife.kotlinbackend.domain.entities.IssueEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

interface BooksService {
    fun getAllBooks(pageable: Pageable): Page<BookEntity>

    fun createBook(bookEntity: BookEntity): BookEntity

    fun updateBook(isbn: String, bookEntity: BookEntity): Optional<BookEntity>

    fun viewBook(isBn: String): BookEntity

    fun getCheckedOutBooks(): List<CheckedOutBook>

    fun getIssues(): List<IssueEntity>

    fun issueBook(isBn: String, issueEntity: IssueEntity): IssueEntity
    fun bookExists(isbn: String): Boolean

    fun deleteBook(isbn: String)

    fun clearIssues()

    fun clearBooks()

}