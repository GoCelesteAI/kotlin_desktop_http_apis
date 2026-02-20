import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
  val latitude: Double,
  val longitude: Double,
  val current: CurrentWeather,
)

@Serializable
data class CurrentWeather(
  val time: String,
  @SerialName("temperature_2m") val temperature: Double,
  @SerialName("weather_code") val weatherCode: Int,
  @SerialName("wind_speed_10m") val windSpeed: Double,
)

