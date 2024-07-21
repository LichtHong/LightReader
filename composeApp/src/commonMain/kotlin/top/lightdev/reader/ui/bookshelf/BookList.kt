package top.lightdev.reader.ui.bookshelf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemContentType
import app.cash.paging.compose.itemKey
import top.lightdev.reader.data.entity.Book
import top.lightdev.reader.ui.book.BookCard

val lazyVerticalGridCells = GridCells.Adaptive(100.dp)

@Composable
fun BookGrid(
    selectedBook: Book?,
    bookLazyGridState: LazyGridState,
    bookLazyPagingItems: LazyPagingItems<Book>,
    supportingPaneExpanded: Boolean,
    updateUIStateSelectedBook: (Book) -> Unit
) {
    val inverseOnSurface = MaterialTheme.colorScheme.inverseOnSurface
    LazyVerticalGrid(
        columns = lazyVerticalGridCells,
        contentPadding = PaddingValues(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = bookLazyGridState,
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = bookLazyPagingItems.itemCount,
            key = bookLazyPagingItems.itemKey { it.id },
            contentType = bookLazyPagingItems.itemContentType { "Book" }
        ) { index ->
            val book = bookLazyPagingItems[index]
            if (book != null) {
                BookCard(
                    name = book.name,
                    author = book.author,
                    /*cover = book.realCover,*/
                    cover = null,
                    progress = book.readProgress,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(12.0.dp)
                        )
                        .fillMaxWidth()
                        .drawBehind {
                            if (supportingPaneExpanded && selectedBook?.id == book.id) {
                                drawRect(inverseOnSurface)
                            }
                        }
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            if (supportingPaneExpanded) {
                                updateUIStateSelectedBook(book)
                            }
                        }
                )
            }
        }
    }
}
