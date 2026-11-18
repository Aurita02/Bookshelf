
package com.apps.bookshelf.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.apps.bookshelf.model.Book

@Composable
fun BooksScreen(
    uiState: BooksUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is BooksUiState.Loading -> {
            LoadingScreen(modifier)
        }

        is BooksUiState.Success -> {
            BooksGrid(
                books = uiState.books,
                modifier = modifier
            )
        }

        is BooksUiState.Error -> {
            ErrorScreen(
                onRetry = onRetry,
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No se pudieron cargar los libros"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = onRetry
        ) {
            Text("Reintentar")
        }
    }
}

@Composable
fun BooksGrid(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = books,
            key = { it.id }
        ) { book ->
            BookCard(book = book)
        }
    }
}

@Composable
fun BookCard(
    book: Book
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = book.volumeInfo.imageLinks
                    ?.thumbnail
                    ?.replace("http://", "https://"),
                contentDescription = book.volumeInfo.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
