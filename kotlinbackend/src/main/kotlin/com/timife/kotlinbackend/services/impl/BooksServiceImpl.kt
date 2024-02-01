package com.timife.kotlinbackend.services.impl

import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.CheckedOutBook
import com.timife.kotlinbackend.repositories.BooksRepository
import com.timife.kotlinbackend.services.BooksService
import org.springframework.stereotype.Service

@Service
class BooksServiceImpl (
    private val bookRepository: BooksRepository
): BooksService {

    override fun getAllBooks(): List<BookEntity> {
        return bookRepository.findAll().toList()
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
}