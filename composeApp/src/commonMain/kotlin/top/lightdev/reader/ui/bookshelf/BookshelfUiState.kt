package top.lightdev.reader.ui.bookshelf

import top.lightdev.reader.data.entity.Book

data class BookshelfUiState(
    val selectedBook: Book? = null,
)