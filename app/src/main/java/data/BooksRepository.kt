
package com.apps.bookshelf.data

import com.apps.bookshelf.BuildConfig
import com.apps.bookshelf.model.Book
import com.apps.bookshelf.network.BooksApiService
import kotlinx.coroutines.CancellationException

interface BooksRepository {
    suspend fun getBooks(): List<Book>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(): List<Book> {

        val response = booksApiService.searchBooks(
            query = "programming",
            apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
        )

        return response.items.map { book ->

            try {
                booksApiService.getBook(
                    id = book.id,
                    apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                book
            }
        }
    }
}
