package dev.jamieastley.kmpihole.api.models.teleporter

import kotlinx.serialization.Serializable

@Serializable
data class TeleporterImportResponse(
    val processed: List<String> = emptyList(),
    val took: Double = 0.0,
)
