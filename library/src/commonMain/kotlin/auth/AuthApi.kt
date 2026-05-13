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

    /**
     * Create a new application password.
     *
     * From the Pi-Hole documentation:
     * > Create a new application password. The generated password is shown only once and cannot
     * > be retrieved later - make sure to store it in a safe place. The application password can be
     * > used to authenticate against the API instead of the regular password. It does not require
     * > 2FA verification. Generating a new application password will invalidate all currently active
     * > sessions.
     *
     * > Note that this endpoint only generates an application password accompanied by its hash.
     * > To make this new password effective, the returned hash has to be set as
     * > `webserver.api.app_password` in the Pi-hole configuration in a follow-up step.
     *
     * > This can be done in various ways, e.g. via the API (PATCH /api/config/webserver/api/app_pwhash),
     * > the graphical web interface (Settings -> All Settings) or by editing the configuration file directly.
     */
    suspend fun createAppPassword(): AppPasswordResponse
}
