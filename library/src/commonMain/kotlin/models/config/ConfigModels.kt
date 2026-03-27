package dev.jamieastley.kmpihole.api.models.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ConfigResponse(
    val config: JsonObject,
    val took: Double = 0.0,
)

@Serializable
data class PatchConfigRequest(val config: JsonObject)
