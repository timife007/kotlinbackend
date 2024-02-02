package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.dtos.BookDto
import com.timife.kotlinbackend.domain.dtos.IssueDto
import com.timife.kotlinbackend.domain.entities.IssueEntity
import com.timife.kotlinbackend.domain.response.ErrorResponse
import com.timife.kotlinbackend.presentation.utils.toBookDto
import com.timife.kotlinbackend.presentation.utils.toBookEntity
import com.timife.kotlinbackend.services.BooksService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/books")
class BooksController(
    private val booksService: BooksService
) {

    @GetMapping
    fun getAllBooks(pageable: Pageable): ResponseEntity<Page<BookDto>> {
        return ResponseEntity.ok(booksService.getAllBooks(pageable).map {
            it.toBookDto()
        })
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
    fun updateBook(@PathVariable isbn: String, @RequestBody bookDto: BookDto) {
        return try {

        } catch (e: Exception) {

        }
    }

    @GetMapping("/issued")
    fun viewIssues(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(booksService.getIssues())
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.localizedMessage)
        }
    }

    @PutMapping("/issue/{isbn}")
    fun issueBook(
        @PathVariable isbn: String,
        @RequestBody issueDto: IssueDto
    ): ResponseEntity<Any> {
        val book = booksService.viewBook(isbn)
        val issueEntity = IssueEntity(
            title = book.title,
            author = book.author,
            isbn = issueDto.isbn,
            person = issueDto.person,
            issueDate = LocalDateTime.now()
        )
        return try {
            ResponseEntity.ok(issueEntity)
        } catch (e: Exception) {
            val error = ErrorResponse(status = HttpStatus.UNAUTHORIZED, message = e.localizedMessage, null)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error)
        }
    }
}