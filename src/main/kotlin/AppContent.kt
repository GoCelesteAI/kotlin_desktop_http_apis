import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AppContent() {
  var selectedCity by remember { mutableStateOf(cities[0]) }
  var weather by remember { mutableStateOf<WeatherResponse?>(null) }
  var loading by remember { mutableStateOf(false) }
  var error by remember { mutableStateOf<String?>(null) }

  val scope = rememberCoroutineScope()

  fun loadWeather(city: City) {
    loading = true
    error = null
    scope.launch {
      try {
        weather = fetchWeather(city)
      } catch (e: Exception) {
        error = "Failed to fetch weather: ${e.message}"
      } finally {
        loading = false
      }
    }
  }

  LaunchedEffect(Unit) { loadWeather(selectedCity) }

  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
      Text("Weather App", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)

      Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        cities.forEach { city ->
          Button(
            onClick = { selectedCity = city; loadWeather(city) },
            colors = if (city == selectedCity) ButtonDefaults.buttonColors()
              else ButtonDefaults.outlinedButtonColors(),
          ) { Text(city.name) }
        }
        IconButton(onClick = { loadWeather(selectedCity) }) {
          Icon(Icons.Default.Refresh, "Refresh")
        }
      }

      when {
        loading -> CircularProgressIndicator()
        error != null -> Text(error!!, color = MaterialTheme.colorScheme.error)
        weather != null -> WeatherCard(weather!!)
      }
    }
  }
}

@Composable
fun WeatherCard(data: WeatherResponse) {
  Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text("Temperature: ${data.current.temperature}°C", style = MaterialTheme.typography.headlineMedium)
      Text("Wind Speed: ${data.current.windSpeed} km/h", style = MaterialTheme.typography.bodyLarge)
      Text("Weather Code: ${data.current.weatherCode}", style = MaterialTheme.typography.bodyLarge)
      Text("Time: ${data.current.time}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
  }
}

