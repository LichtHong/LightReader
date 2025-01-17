import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import top.lightdev.reader.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Light Reader",
    ) {
        App()
    }
}