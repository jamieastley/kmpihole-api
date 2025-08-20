package dev.jamieastley.kmpihole.api.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val session: Session)

@Serializable
data class Session(
    val valid: Boolean,
    val totp: Boolean,
    val sid: String,
    val csrf: String,
    val validity: Int,
    val message: String
)
