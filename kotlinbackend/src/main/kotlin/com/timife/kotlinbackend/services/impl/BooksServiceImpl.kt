package com.timife.kotlinbackend.services.impl

import com.timife.kotlinbackend.domain.entities.BookEntity
import com.timife.kotlinbackend.domain.entities.CheckedOutBook
import com.timife.kotlinbackend.domain.entities.IssueEntity
import com.timife.kotlinbackend.repositories.BooksRepository
import com.timife.kotlinbackend.repositories.IssueRepository
import com.timife.kotlinbackend.services.BooksService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class BooksServiceImpl(
    private val bookRepository: BooksRepository,
    private val issueRepository: IssueRepository
) : BooksService {

    override fun getAllBooks(pageable: Pageable): Page<BookEntity> {
        return bookRepository.findAll(pageable)
    }

    override fun createBook(bookEntity: BookEntity): BookEntity {
        if (bookRepository.existsById(bookEntity.isbn)) {
            val book = bookRepository.findById(bookEntity.isbn).orElseThrow()
            return bookRepository.save(book.copy(quantity = book.quantity + 1))
        }
        return bookRepository.save(bookEntity)
    }

    override fun updateBook(isbn: String, bookEntity: BookEntity): Optional<BookEntity> {
        return bookRepository.findById(isbn).map { book ->
            bookRepository.save(book)
        } ?: throw IllegalArgumentException()
    }

    override fun viewBook(isBn: String): BookEntity {
        return bookRepository.findById(isBn).orElseThrow()
    }

    override fun getCheckedOutBooks(): List<CheckedOutBook> {
        TODO("Not yet implemented")
    }

    override fun getIssues(): List<IssueEntity> {
        return StreamSupport.stream(issueRepository.findAll().spliterator(), false).collect(Collectors.toList())
    }

    override fun issueBook(isBn: String, issueEntity: IssueEntity): IssueEntity {
        bookRepository.findById(isBn).map { book ->
            val existingBook = book.copy(quantity = book.quantity - 1)
            bookRepository.save(existingBook)
        }
        return issueRepository.save(issueEntity)
    }

    override fun bookExists(isbn: String): Boolean {
        return bookRepository.existsById(isbn)
    }

    override fun deleteBook(isbn: String) {
        bookRepository.deleteById(isbn)
    }
}