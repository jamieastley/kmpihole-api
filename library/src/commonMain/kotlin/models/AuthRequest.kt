package dev.jamieastley.kmpihole.api.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(val password: String)