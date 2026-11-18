
package com.apps.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apps.bookshelf.data.BooksRepository
import com.apps.bookshelf.model.Book
import kotlinx.coroutines.launch

sealed interface BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState
    data object Error : BooksUiState
    data object Loading : BooksUiState
}

class BooksViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(
        BooksUiState.Loading
    )
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading

            booksUiState = try {
                BooksUiState.Success(
                    booksRepository.getBooks()
                )
            } catch (e: Exception) {
                BooksUiState.Error
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: BooksRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>
                ): T {
                    return BooksViewModel(repository) as T
                }
            }
    }
}
