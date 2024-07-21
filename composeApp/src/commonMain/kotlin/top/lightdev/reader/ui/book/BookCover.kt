package top.lightdev.reader.ui.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import top.lightdev.reader.ui.image.BookCover
import top.lightdev.reader.ui.image.Images
import top.lightdev.reader.ui.image.rememberVectorPainter

@Composable
fun BookCover(
    name: String,
    author: String,
    cover: ByteArray?,
    modifier: Modifier
) {
    Surface(
        shape = RoundedCornerShape(4.0.dp),
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            if (cover != null) {
                AsyncImage(
                    model = cover,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                DefaultBookCover(name, author)
            }
        }
    }
}

@Composable
fun DefaultBookCover(
    name: String,
    author: String
) {
    val painter = rememberVectorPainter(
        image = Images.BookCover,
        tintColor = MaterialTheme.colorScheme.onSurface
    )
    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentScale = ContentScale.FillBounds
    )
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(0.75f)
    ) {
        val nameFontSize = (maxWidth.value / 6).sp
        val authorFontSize = (maxWidth.value / 10).sp
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = name,
                fontSize = nameFontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$author 著",
                fontSize = authorFontSize,
                textAlign = TextAlign.Center
            )
        }
    }
}