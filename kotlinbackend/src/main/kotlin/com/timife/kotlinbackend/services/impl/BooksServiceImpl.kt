package com.timife.kotlinbackend.services.impl

import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.CheckedOutBook
import com.timife.kotlinbackend.repositories.BooksRepository
import com.timife.kotlinbackend.services.BooksService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BooksServiceImpl(
    private val bookRepository: BooksRepository
) : BooksService {

    override fun getAllBooks(pageable: Pageable): Page<BookEntity> {
        return bookRepository.findAll(pageable)
    }

    override fun createBook(bookEntity: BookEntity): BookEntity {
        TODO("Not yet implemented")
    }

    override fun updateBook(bookEntity: BookEntity): BookEntity {
        TODO("Not yet implemented")
    }

    override fun viewBook(isBn: String): BookEntity {
        TODO("Not yet implemented")
    }

    override fun getCheckedOutBooks(): List<CheckedOutBook> {
        TODO("Not yet implemented")
    }

    override fun getIssuedBooks(): List<BookEntity> {
        TODO("Not yet implemented")
    }

    override fun issueBook(isBn: String): BookEntity {
        TODO("Not yet implemented")
    }

    override fun bookExists(isbn: String): Boolean {
        return bookRepository.findAll().any {
            it.isbn == isbn
        }
    }

    override fun getUnissuedBook(isBn: String): BookEntity {
        TODO("Not yet implemented")
    }
}