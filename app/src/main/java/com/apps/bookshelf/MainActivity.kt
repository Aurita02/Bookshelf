

package com.apps.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.bookshelf.data.DefaultAppContainer
import com.apps.bookshelf.ui.screens.BooksScreen
import com.apps.bookshelf.ui.screens.BooksViewModel

class MainActivity : ComponentActivity() {

    private val container = DefaultAppContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                BookshelfApp(
                    container = container
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(
    container: DefaultAppContainer
) {
    val booksViewModel: BooksViewModel = viewModel(
        factory = BooksViewModel.provideFactory(
            container.booksRepository
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Bookshelf")
                }
            )
        }
    ) { innerPadding ->

        BooksScreen(
            uiState = booksViewModel.booksUiState,
            onRetry = booksViewModel::getBooks,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
