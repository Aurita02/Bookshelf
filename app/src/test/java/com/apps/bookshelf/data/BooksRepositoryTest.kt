package com.apps.bookshelf.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksRepositoryTest {

    @Test
    fun getBooks_retornaListaDeLibros() = runBlocking {

        val fakeService = FakeBooksApiService()

        val repository = NetworkBooksRepository(
            fakeService
        )

        val books = repository.getBooks()

        assertEquals(2, books.size)

        assertEquals(
            "Aprendiendo Kotlin",
            books[0].volumeInfo.title
        )

        assertEquals(
            "Programación Android",
            books[1].volumeInfo.title
        )
    }
}
