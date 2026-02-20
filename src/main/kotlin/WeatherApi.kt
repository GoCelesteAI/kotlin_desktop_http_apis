import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val client = HttpClient(CIO) {
  install(ContentNegotiation) {
    json(Json { ignoreUnknownKeys = true })
  }
}

data class City(val name: String, val latitude: Double, val longitude: Double)

val cities = listOf(
  City("Tokyo", 35.6762, 139.6503),
  City("London", 51.5074, -0.1278),
  City("New York", 40.7128, -74.0060),
)

suspend fun fetchWeather(city: City): WeatherResponse {
  return client.get("https://api.open-meteo.com/v1/forecast") {
    parameter("latitude", city.latitude)
    parameter("longitude", city.longitude)
    parameter("current", "temperature_2m,weather_code,wind_speed_10m")
  }.body()
}

