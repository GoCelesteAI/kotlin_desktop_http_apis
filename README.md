# Kotlin Desktop: HTTP & APIs

Build a Weather App with Compose Desktop using Ktor Client and the free Open-Meteo API (no API key required).

## Source Files

| File | Description |
|------|-------------|
| `src/main/kotlin/WeatherModels.kt` | `@Serializable` data classes for API response mapping with `@SerialName` |
| `src/main/kotlin/WeatherApi.kt` | `HttpClient(CIO)` with `ContentNegotiation`, `City` data class, `fetchWeather` suspend function |
| `src/main/kotlin/AppContent.kt` | Main composable with state management, coroutine-based loading, city buttons, `when` block, `WeatherCard` |
| `src/main/kotlin/Main.kt` | Application window (900x600) with Material3 dark theme |

## Prerequisites

- Java 17+ (JDK)
- Gradle 8.x

## Usage

```bash
./gradlew run
```

Click city buttons (Tokyo, London, New York) to fetch live weather data. Use the refresh button to reload.

## Topics Covered

- Ktor Client with CIO engine for HTTP requests
- ContentNegotiation plugin with JSON serialization
- `@Serializable` data classes for API response mapping
- `@SerialName` for mapping JSON field names to Kotlin properties
- Suspend functions for asynchronous network calls
- `rememberCoroutineScope` and `scope.launch` for UI coroutines
- `LaunchedEffect` for initial data fetch on composition
- Loading states with `CircularProgressIndicator`
- Error handling with try-catch and user-friendly messages
- `when` block for conditional UI rendering (loading/error/success)

## Tech Stack

- Kotlin 2.1.0
- Compose Multiplatform 1.7.3
- Material3
- Ktor Client 3.0.3 (CIO engine)
- kotlinx-serialization 1.7.3

## API

[Open-Meteo](https://open-meteo.com) — free weather API, no API key required.

---

Taught by CelesteAI
