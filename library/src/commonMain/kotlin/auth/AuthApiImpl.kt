package dev.jamieastley.kmpihole.api.auth

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.SessionManager
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.auth.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class AuthApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
    private val sessionManager: SessionManager,
) : AuthApi {

    override suspend fun checkAuth(): AuthResponse =
        client.get("${config.baseUrl}/auth").bodyOrThrow()

    override suspend fun login(request: AuthRequest): AuthResponse {
        val response: AuthResponse = client.post("${config.baseUrl}/auth") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()
        response.session.sid?.let { sessionManager.store(it) }
        return response
    }

    override suspend fun logout() {
        val response = client.delete("${config.baseUrl}/auth")
        sessionManager.clear()
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun getSessions(): SessionListResponse =
        client.get("${config.baseUrl}/auth/sessions").bodyOrThrow()

    override suspend fun deleteSession(id: Int) {
        val response = client.delete("${config.baseUrl}/auth/session/$id")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun getTotpSuggestion(): TotpResponse =
        client.get("${config.baseUrl}/auth/totp").bodyOrThrow()

    override suspend fun createAppPassword(): AppPasswordResponse =
        client.get("${config.baseUrl}/auth/app").bodyOrThrow()
}
