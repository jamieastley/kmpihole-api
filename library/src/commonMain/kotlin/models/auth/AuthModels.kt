package dev.jamieastley.kmpihole.api.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The request body for the `/auth/session` endpoint.
 *
 * @param password The password to authenticate with.
 * @param totp The TOTP code to authenticate with.
 */
@Serializable
data class AuthRequest(
    val password: String,
    val totp: String? = null,
)

/**
 * The response returned from the `/auth/session` endpoint.
 *
 * @param session The session information returned after authentication.
 * @param took The time taken to process the request in seconds.
 */
@Serializable
data class AuthResponse(
    val session: Session,
    val took: Double = 0.0,
)

/**
 * Represents the session state returned after an authentication attempt.
 *
 * @param valid Valid session indicator which defines whether the client is authenticated.
 * @param totp Whether 2FA (TOTP) is enabled on this Pi-Hole.
 * @param sid The current session ID.
 * @param csrf The CSRF token.
 * @param validity The remaining lifetime (in seconds) of this session unless refreshed.
 * @param message A human-readable message describing the session status.
 */
@Serializable
data class Session(
    val valid: Boolean,
    val totp: Boolean,
    val sid: String? = null,
    val csrf: String? = null,
    val validity: Int,
    val message: String? = null,
)

/**
 * The response returned from the `auth/sessions` endpoint.
 *
 * @param sessions The list of all current sessions.
 * @param took The time taken to process the request in seconds.
 */
@Serializable
data class SessionListResponse(
    val sessions: List<SessionInfo>,
    val took: Double = 0.0,
)

/**
 * Session information for a user's session including their validity and further information about
 * the client, such as the IP address and user agent.
 *
 * @param id The session ID.
 * @param currentSession Indicates whether this is the current session.
 * @param valid Indicates whether the session is valid - existing sessions may be invalid due to timeout.
 * @param tls TLS information for the session.
 * @param app Indicates whether the session was initiated using an application password.
 * @param cli Indicates whether this session was initiated using the command-line interface (CLI).
 * @param loginAt Timestamp of login, in seconds since epoch.
 * @param lastActive Timestamp of last activity, in seconds since epoch.
 * @param validUntil Timestamp of session expiration, in seconds since epoch.
 * @param remoteAddress The IP address of the client.
 * @param userAgent The user agent of the client.
 * @param xForwardedFor The IP address of the client (if it's behind a proxy).
 */
@Serializable
data class SessionInfo(
    val id: Int,
    @SerialName("current_session") val currentSession: Boolean,
    val valid: Boolean,
    val tls: TlsInfo? = null,
    val app: Boolean,
    val cli: Boolean,
    @SerialName("login_at") val loginAt: Long,
    @SerialName("last_active") val lastActive: Long,
    @SerialName("valid_until") val validUntil: Long,
    @SerialName("remote_addr") val remoteAddress: String,
    @SerialName("user_agent") val userAgent: String? = null,
    @SerialName("x_forwarded_for") val xForwardedFor: String? = null,
)

/**
 * TLS information for a session.
 *
 * @param login Indicates if TLS (end-to-end encryption) has been used during login for this session.
 * @param mixed Indicates if TLS (end-to-end encryption) has been only partially used for this session.
 */
@Serializable
data class TlsInfo(
    val login: Boolean,
    val mixed: Boolean,
)

/**
 * The TOTP secret suggestion returned from the `auth/totp` endpoint.
 *
 * @param totp The suggested TOTP credentials for two-factor authentication (2FA).
 * @param took The time taken to process the request in seconds.
 */
@Serializable
data class TotpResponse(
    val totp: TotpCredentials,
    val took: Double = 0.0,
)

@Serializable
data class TotpCredentials(
    val type: String,
    val account: String,
    val issuer: String,
    val algorithm: String,
    val digits: Int,
    val period: Int,
    val offset: Int,
    val secret: String,
    val codes: List<Int>
)

/**
 * The application password returned from the `/auth/app` endpoint.
 *
 * @param app The application password for the user.
 * @param took The time taken to process the request in seconds.
 */
@Serializable
data class AppPasswordResponse(
    val app: AppPassword,
    val took: Double = 0.0,
)

@Serializable
data class AppPassword(val password: String, val hash: String)
