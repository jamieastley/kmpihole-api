package dev.jamieastley.kmpihole.api

import dev.jamieastley.kmpihole.api.models.AuthRequest
import dev.jamieastley.kmpihole.api.models.AuthResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun createPlatformHttpClientEngine(): HttpClientEngine

fun createDefaultHttpClient(engine: HttpClientEngine = createPlatformHttpClientEngine()): HttpClient =
    HttpClient(engine) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        defaultRequest {
            contentType(Json)
        }
    }

class KmpiholeClient(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) {

    suspend fun checkAuth(): AuthResponse {
        return httpClient.get("$baseUrl/auth").body()
    }

    suspend fun auth(authRequest: AuthRequest): AuthResponse {
        return httpClient.post("$baseUrl/auth") {
            setBody(authRequest)
        }.body()
    }
}