package top.lightdev.reader.ui.book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BookCard(
    modifier: Modifier,
    name: String,
    author: String,
    cover: ByteArray?,
    progress: Float
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        BookCover(
            name = name,
            author = author,
            cover = cover,
            modifier = Modifier.aspectRatio(0.75f)
        )
        Spacer(Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name)
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(14.dp),
                color = MaterialTheme.colorScheme.inversePrimary,
                strokeWidth = 2.5.dp,
                trackColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}