
package com.apps.bookshelf.model

data class BooksResponse(
    val items: List<Book> = emptyList()
)

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String = "Sin título",
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)
