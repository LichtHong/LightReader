package top.lightdev.reader.ui.bookshelf

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import top.lightdev.reader.ui.Bookshelf
import top.lightdev.reader.ui.book.BookDetail


val bookshelfTabs = listOf(
    Bookshelf.All,
    Bookshelf.Reading,
    Bookshelf.Unread,
    Bookshelf.Markread
)

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun BookshelfScreen(
    type: String = "reading",
    viewModel: BookshelfViewModel = viewModel { BookshelfViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()
    val supportingPaneScaffoldNavigator = rememberSupportingPaneScaffoldNavigator()
    val supportingPaneExpanded by remember {
        derivedStateOf { supportingPaneScaffoldNavigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Expanded }
    }
    val allBooksLazyPagingItems = viewModel.allBooksFlow.collectAsLazyPagingItems()
    val readingBooksLazyPagingItems = viewModel.readingBooksFlow.collectAsLazyPagingItems()
    val unreadBooksLazyPagingItems = viewModel.unreadBooksFlow.collectAsLazyPagingItems()
    val markreadBooksLazyPagingItems = viewModel.markreadBooksFlow.collectAsLazyPagingItems()
    val bookLazyPagingItemsList = remember {
        listOf(
            allBooksLazyPagingItems,
            readingBooksLazyPagingItems,
            unreadBooksLazyPagingItems,
            markreadBooksLazyPagingItems
        )
    }
    val pagerState = rememberPagerState(
        initialPage = when (type) {
            "all" -> 0
            "reading" -> 1
            "unread" -> 2
            "markread" -> 3
            else -> 1
        },
        pageCount = { 4 }
    )
    val scope = rememberCoroutineScope()
    SupportingPaneScaffold(
        directive = supportingPaneScaffoldNavigator.scaffoldDirective,
        value = supportingPaneScaffoldNavigator.scaffoldValue,
        mainPane = {
            AnimatedPane(modifier = Modifier.safeContentPadding()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PrimaryScrollableTabRow(
                        selectedTabIndex = pagerState.currentPage,
                        divider = { },
                        edgePadding = 0.dp
                    ) {
                        repeat(bookshelfTabs.size) { index ->
                            val selected = pagerState.currentPage == index
                            Tab(
                                selected = selected,
                                onClick = {
                                    if (!selected) {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                                },
                                text = { Text(stringResource(bookshelfTabs[index].title)) },
                            )
                        }
                    }

                    val allBooksLazyGridState = rememberLazyGridState()
                    val readingBooksLazyGridState = rememberLazyGridState()
                    val unreadBooksLazyGridState = rememberLazyGridState()
                    val markreadBooksLazyGridState = rememberLazyGridState()
                    val bookLazyGridStateList = remember {
                        listOf(
                            allBooksLazyGridState,
                            readingBooksLazyGridState,
                            unreadBooksLazyGridState,
                            markreadBooksLazyGridState
                        )
                    }

                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) { page ->
                        BookGrid(
                            uiState.selectedBook,
                            bookLazyGridStateList[page],
                            bookLazyPagingItemsList[page],
                            supportingPaneExpanded,
                            viewModel::updateUIStateSelectedBook
                        )
                    }
                }

                LaunchedEffect(pagerState.settledPage) {
                    snapshotFlow { pagerState.settledPage }.collect { page ->
                        if (bookLazyPagingItemsList[page].itemCount > 0) {
                            bookLazyPagingItemsList[page][0]?.let {
                                viewModel.updateUIStateSelectedBook(it)
                            }
                        }
                    }
                }
            }
        },
        supportingPane = {
            AnimatedPane(modifier = Modifier.safeContentPadding()) {
                AnimatedVisibility(uiState.selectedBook != null) {
                    BookDetail(uiState.selectedBook!!)
                }
            }
        }
    )
}