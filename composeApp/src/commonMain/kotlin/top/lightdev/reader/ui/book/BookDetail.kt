package top.lightdev.reader.ui.book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.lightdev.reader.data.entity.Book

@Composable
fun BookDetail(
    selectedBook: Book
) {
    Surface(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BookCover(
                            name = selectedBook.name,
                            author = selectedBook.author,
                            /*cover = selectedBook.realCover,*/
                            cover = null,
                            modifier = Modifier.fillMaxWidth(0.36f).aspectRatio(0.75f)
                        )
                        Spacer(modifier = Modifier.fillMaxWidth(0.075f))
                        Column(
                            modifier = Modifier.fillMaxWidth().aspectRatio(1.177f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = selectedBook.name,
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(0.16f))
                            Text(
                                text = selectedBook.author,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(0.12f))
                            selectedBook.tag?.let { BookTags(it.split(",")) }
                        }
                    }
                }
                item { HorizontalDivider() }
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "简介",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = selectedBook.realIntro,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                item { HorizontalDivider() }
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "目录",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier.fillMaxWidth().height(256.dp))
                    }
                }
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("阅读")
            }
        }
    }
}

@Composable
fun BookTags(tags: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tags.forEachIndexed { index, tag ->
            if (tag.isNotBlank()) {
                Text(
                    text = tag,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
                if (index != tags.lastIndex) {
                    Text(
                        text = "·",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}