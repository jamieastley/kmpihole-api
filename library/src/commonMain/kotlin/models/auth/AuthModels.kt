package dev.jamieastley.kmpihole.api.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val password: String,
    val totp: String? = null,
)

@Serializable
data class AuthResponse(
    val session: Session,
    val took: Double = 0.0,
)

@Serializable
data class Session(
    val valid: Boolean,
    val totp: Boolean,
    val sid: String? = null,
    val csrf: String? = null,
    val validity: Int,
    val message: String? = null,
)

@Serializable
data class SessionListResponse(
    val sessions: List<SessionInfo>,
    val took: Double = 0.0,
)

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
    @SerialName("remote_addr") val remoteAddr: String,
    @SerialName("user_agent") val userAgent: String? = null,
    @SerialName("x_forwarded_for") val xForwardedFor: String? = null,
)

@Serializable
data class TlsInfo(
    val login: Boolean,
    val mixed: Boolean,
)

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

@Serializable
data class AppPasswordResponse(
    val app: AppPassword,
    val took: Double = 0.0,
)

@Serializable
data class AppPassword(val password: String)
