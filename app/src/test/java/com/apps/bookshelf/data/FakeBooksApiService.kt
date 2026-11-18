package com.apps.bookshelf.data

import com.apps.bookshelf.model.Book
import com.apps.bookshelf.model.BooksResponse
import com.apps.bookshelf.model.VolumeInfo
import com.apps.bookshelf.model.ImageLinks
import com.apps.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {

    override suspend fun searchBooks(
        query: String,
        apiKey: String
    ): BooksResponse {
        return BooksResponse(
            items = listOf(
                Book(
                    id = "libro1",
                    volumeInfo = VolumeInfo(
                        title = "Aprendiendo Kotlin",
                        imageLinks = ImageLinks(
                            thumbnail = "https://example.com/libro1.jpg"
                        )
                    )
                ),
                Book(
                    id = "libro2",
                    volumeInfo = VolumeInfo(
                        title = "Programación Android",
                        imageLinks = ImageLinks(
                            thumbnail = "https://example.com/libro2.jpg"
                        )
                    )
                )
            )
        )
    }

    override suspend fun getBook(
        id: String,
        apiKey: String
    ): Book {
        return searchBooks("", "")
            .items.first { it.id == id }
    }
}
