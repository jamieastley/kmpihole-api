package dev.jamieastley.kmpihole.api.auth

import dev.jamieastley.kmpihole.api.models.auth.*

interface AuthApi {
    /** Check if authentication is required and return the current session state. */
    suspend fun checkAuth(): AuthResponse

    /** Login with a password (and optional TOTP code). Stores the session ID internally. */
    suspend fun login(request: AuthRequest): AuthResponse

    /** Logout and invalidate the current session. */
    suspend fun logout()

    /** Return a list of all active sessions. */
    suspend fun getSessions(): SessionListResponse

    /** Delete a specific session by its ID. */
    suspend fun deleteSession(id: Int)

    /** Suggest new TOTP credentials for 2FA setup. */
    suspend fun getTotpSuggestion(): TotpResponse

    /** Create a new application password. */
    suspend fun createAppPassword(): AppPasswordResponse
}
