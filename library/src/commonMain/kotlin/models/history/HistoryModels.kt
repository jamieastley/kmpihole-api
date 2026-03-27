package dev.jamieastley.kmpihole.api.models.history

import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    val history: List<HistoryPoint> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class HistoryPoint(
    val timestamp: Double,
    val total: Int,
    val cached: Int,
    val blocked: Int,
    val forwarded: Int,
)

@Serializable
data class ClientHistoryResponse(
    val clients: Map<String, ClientMeta> = emptyMap(),
    val history: List<ClientHistoryPoint> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class ClientMeta(
    val name: String? = null,
    val total: Int,
)

@Serializable
data class ClientHistoryPoint(
    val timestamp: Double,
    val data: Map<String, Int> = emptyMap(),
)
