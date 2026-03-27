package dev.jamieastley.kmpihole.api.models.action

import kotlinx.serialization.Serializable

@Serializable
data class ActionResponse(
    val status: String? = null,
    val took: Double = 0.0,
)
