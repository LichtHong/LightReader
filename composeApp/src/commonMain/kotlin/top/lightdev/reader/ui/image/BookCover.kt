package top.lightdev.reader.ui.image

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Images.BookCover: ImageVector
    get() {
        if (_bookCover != null) {
            return _bookCover!!
        }
        _bookCover = ImageVector.Builder(
            name = "BookCover",
            defaultWidth = 18.dp,
            defaultHeight = 24.dp,
            viewportWidth = 18f,
            viewportHeight = 24f
        ).path(
            stroke = SolidColor(Color.Black),
            strokeAlpha = 1f,
            strokeLineWidth = 0.18f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero,
            pathBuilder = {
                moveTo(1.8f, 2.4f)
                horizontalLineToRelative(14.4f)
                verticalLineToRelative(19.2f)
                horizontalLineToRelative(-14.4f)
                verticalLineToRelative(-19.2f)
                close()
            }
        ).build()
        return _bookCover!!
    }

private var _bookCover: ImageVector? = null