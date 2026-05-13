package dev.jamieastley.kmpihole.api

import dev.jamieastley.kmpihole.api.auth.AuthApi
import dev.jamieastley.kmpihole.api.auth.AuthApiImpl
import dev.jamieastley.kmpihole.api.internal.SessionManager
import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun createPlatformHttpClientEngine(): HttpClientEngine

/**
 * Main entry point for the KMPiHole API client.
 *
 * Usage example:
 * ```kotlin
 * val client = KmpiholeClient(KmpiholeClientConfig(host = "192.168.1.1", useTls = false))
 * client.auth.login(AuthRequest(password = "secret"))
 * val summary = client.stats.getSummary()
 * ```
 */
class KmpiholeClient(
    clientConfig: KmpiholeClientConfig,
    engine: HttpClientEngine = createPlatformHttpClientEngine(),
) : KmpiholeClientApi {
    companion object {
        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }

    private val sessionManager = SessionManager()

    private val httpClient: HttpClient = HttpClient(engine) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(HttpTimeout) {
            connectTimeoutMillis = clientConfig.connectTimeoutMs
            requestTimeoutMillis = clientConfig.requestTimeoutMs
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            sessionManager.sid?.let { headers.append("sid", it) }
        }
    }

    override val auth: AuthApi = AuthApiImpl(httpClient, clientConfig, sessionManager)
}