import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "Weather App",
    state = rememberWindowState(size = DpSize(900.dp, 600.dp)),
  ) {
    MaterialTheme(colorScheme = darkColorScheme()) {
      AppContent()
    }
  }
}

