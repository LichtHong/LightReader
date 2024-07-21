package top.lightdev.reader.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import top.lightdev.reader.resources.Res
import top.lightdev.reader.resources.all
import top.lightdev.reader.resources.bookshelf
import top.lightdev.reader.resources.browse
import top.lightdev.reader.resources.markread
import top.lightdev.reader.resources.profile
import top.lightdev.reader.resources.reading
import top.lightdev.reader.resources.unread


sealed interface Screen {
    val route: String
    val title: StringResource
}

sealed interface ScreenWithIcons : Screen {
    override val route: String
    override val title: StringResource
    val icon: ImageVector
    val selectedIcon: ImageVector
}

data object Browse : ScreenWithIcons {
    override val route = "/browse"
    override val title = Res.string.browse
    override val icon = Icons.Outlined.Explore
    override val selectedIcon = Icons.Filled.Explore
}

data object Bookshelf : ScreenWithIcons {
    override val route = "/bookshelf"
    override val title = Res.string.bookshelf
    override val icon = Icons.Outlined.AutoStories
    override val selectedIcon = Icons.Filled.AutoStories

    data object All : Screen {
        override val route = "/bookshelf/all"
        override val title = Res.string.all
    }

    data object Reading : Screen {
        override val route = "/bookshelf/reading"
        override val title = Res.string.reading
    }

    data object Unread : Screen {
        override val route = "/bookshelf/unread"
        override val title = Res.string.unread
    }

    data object Markread : Screen {
        override val route = "/bookshelf/markread"
        override val title = Res.string.markread
    }
}

data object Profile : ScreenWithIcons {
    override val route = "/profile"
    override val title = Res.string.profile
    override val icon = Icons.Outlined.Person
    override val selectedIcon = Icons.Filled.Person
}