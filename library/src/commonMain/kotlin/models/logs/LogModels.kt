package dev.jamieastley.kmpihole.api.models.logs

import kotlinx.serialization.Serializable

@Serializable
data class LogResponse(
    val log: List<LogEntry> = emptyList(),
    val nextID: Int? = null,
    val pid: Int? = null,
    val file: String? = null,
    val took: Double = 0.0,
)

@Serializable
data class LogEntry(
    val timestamp: Double,
    val message: String,
    val prio: String? = null,
)
