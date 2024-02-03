package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.dtos.BookDto
import com.timife.kotlinbackend.domain.dtos.IssueDto
import com.timife.kotlinbackend.domain.entities.IssueEntity
import com.timife.kotlinbackend.domain.response.ClearResponse
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.presentation.utils.toBookDto
import com.timife.kotlinbackend.presentation.utils.toBookEntity
import com.timife.kotlinbackend.services.BooksService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/books")
class BooksController(
    private val booksService: BooksService
) {

    @GetMapping
    fun getAllBooks(pageable: Pageable): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(booksService.getAllBooks(pageable).map {
                it.toBookDto()
            })
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.CREATED).body(e.localizedMessage)
        }
    }

    @PostMapping
    fun createBook(@RequestBody book: BookDto): ResponseEntity<Any> {
        val bookEntity = book.toBookEntity()
        val bookSaved = booksService.createBook(bookEntity)
        val savedBookDto = bookSaved.toBookDto()
        return try {
            ResponseEntity.ok(savedBookDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.CREATED).body(e.localizedMessage)
        }
    }

    @PutMapping("/update/{isbn}")
    fun updateBook(@PathVariable isbn: String, @RequestBody bookDto: BookDto): ResponseEntity<Any> {
        val updateBook = booksService.updateBook(isbn, bookDto.toBookEntity()).orElseThrow()
        val returnUpdatedBook = updateBook.toBookDto()
        return try {
            ResponseEntity.ok(returnUpdatedBook)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.CREATED).body(e.localizedMessage)
        }
    }

    @GetMapping("/issued")
    fun viewIssues(): ResponseEntity<Any> {
        val issueEntity = booksService.getIssues()

        return try {
            ResponseEntity.ok(issueEntity)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.localizedMessage)
        }
    }

    @PostMapping("/issue/{isbn}")
    fun issueBook(
        @PathVariable("isbn") isbn: String,
        @RequestBody issueDto: IssueDto
    ): ResponseEntity<Any> {
        val book = booksService.viewBook(isbn)

        val issueEntity = IssueEntity(
            id = null,
            title = book.title,
            author = book.author,
            isbn = issueDto.isbn,
            person = issueDto.person,
            issueDate = LocalDateTime.now(),
            quantity = issueDto.quantity
        )
        val issued = booksService.issueBook(isbn, issueEntity)
        return try {
            ResponseEntity.ok(issued)
        } catch (e: Exception) {
            val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = e.localizedMessage, null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }
    }

    @GetMapping("/{isbn}")
    fun viewBook(
        @PathVariable("isbn") isbn: String
    ): ResponseEntity<Any> {
        return try {
            val book = booksService.viewBook(isbn)
            ResponseEntity.ok(book.toBookDto())
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.localizedMessage)
        }
    }

    @DeleteMapping("/issues")
    fun clearIssues(): ResponseEntity<Any> {
        return try {
            booksService.clearIssues()
            ResponseEntity.ok(ClearResponse("Issues db successfully cleared"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.localizedMessage)
        }
    }

    @DeleteMapping("/{isbn}")
    fun deleteBook(@PathVariable("isbn") isbn: String): ResponseEntity<Any> {
        if (!booksService.bookExists(isbn)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClearResponse("Book not found"))
        }
        return try {
            booksService.deleteBook(isbn)
            ResponseEntity.ok(ClearResponse("Book successfully deleted"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ClearResponse(message = e.localizedMessage))
        }
    }

    @DeleteMapping
    fun clearBooks(): ResponseEntity<Any> {
        return try {
            booksService.clearBooks()
            ResponseEntity.ok(ClearResponse("Books db successfully cleared"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.localizedMessage)
        }
    }

}