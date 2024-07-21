package top.lightdev.reader.ui.bookshelf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import top.lightdev.reader.data.entity.Book
import top.lightdev.reader.data.getDatabaseBuilder
import top.lightdev.reader.data.getRoomDatabase

class BookshelfViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BookshelfUiState())
    val uiState: StateFlow<BookshelfUiState> = _uiState.asStateFlow()

    private val database = getRoomDatabase(getDatabaseBuilder())
    private val bookDao = database.bookDao()
    private val booksPagingConfig = PagingConfig(pageSize = 24)
    private val allBooksPager = Pager(
        config = booksPagingConfig,
        initialKey = null,
        pagingSourceFactory = { bookDao.getAllBooksPagingSource() }
    )
    private val unreadBooksPager = Pager(
        config = booksPagingConfig,
        initialKey = null,
        pagingSourceFactory = { bookDao.getUnreadBooksPagingSource() }
    )
    private val readingBooksPager = Pager(
        config = booksPagingConfig,
        initialKey = null,
        pagingSourceFactory = { bookDao.getReadingBooksPagingSource() }
    )
    private val markreadBooksPager = Pager(
        config = booksPagingConfig,
        initialKey = null,
        pagingSourceFactory = { bookDao.getMarkreadBooksPagingSource() }
    )
    val allBooksFlow = allBooksPager.flow.cachedIn(viewModelScope)
    val unreadBooksFlow = unreadBooksPager.flow.cachedIn(viewModelScope)
    val readingBooksFlow = readingBooksPager.flow.cachedIn(viewModelScope)
    val markreadBooksFlow = markreadBooksPager.flow.cachedIn(viewModelScope)

    fun updateUIStateSelectedBook(book: Book) {
        _uiState.update { currentState ->
            currentState.copy(selectedBook = book)
        }
    }
}