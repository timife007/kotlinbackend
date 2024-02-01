package com.timife.kotlinbackend.presentation.controllers.auth

import com.timife.kotlinbackend.domain.dtos.BookDto
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
    fun createBook(@RequestBody book: BookDto){

    }

    @PutMapping("/update/{isbn}")
    fun updateBook(@PathVariable isbn: String, @RequestBody bookDto: BookDto) {
        return try {

        }catch (e: Exception){

        }
    }

    @GetMapping("/issued")
    fun viewIssuedBooks(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(booksService.getIssuedBooks())
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.localizedMessage)
        }
    }

    @PutMapping("/issue/{isbn}")
    fun issueBook(
        @PathVariable isbn: String
    ): ResponseEntity<Any> {
        val unIssuedBook = booksService.getUnissuedBook(isbn)
        val isPresent = booksService.bookExists(isbn)
        val issuedBook = booksService.updateBook(
            unIssuedBook.copy(isIssued = true)
        )

        return if (isPresent) {
            ResponseEntity.ok(issuedBook)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(HttpStatus.CREATED, "Error issuing book"))
        }
    }
}