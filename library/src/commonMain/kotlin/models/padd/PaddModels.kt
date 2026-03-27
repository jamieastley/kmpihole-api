package dev.jamieastley.kmpihole.api.models.padd

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaddResponse(
    @SerialName("recent_blocked") val recentBlocked: String? = null,
    @SerialName("top_domain") val topDomain: String? = null,
    @SerialName("top_blocked") val topBlocked: String? = null,
    @SerialName("top_client") val topClient: String? = null,
    @SerialName("active_clients") val activeClients: Int? = null,
    @SerialName("gravity_size") val gravitySize: Int? = null,
    val blocking: String? = null,
    val queries: PaddQueryStats? = null,
    val cache: PaddCacheStats? = null,
    val iface: PaddInterfaces? = null,
    val took: Double = 0.0,
)

@Serializable
data class PaddQueryStats(
    val total: Int,
    val blocked: Int,
    @SerialName("percent_blocked") val percentBlocked: Double,
)

@Serializable
data class PaddCacheStats(
    val size: Int,
    val inserted: Int,
    val evicted: Int,
)

@Serializable
data class PaddInterfaces(
    val v4: PaddInterface? = null,
    val v6: PaddInterface? = null,
)

@Serializable
data class PaddInterface(
    val addr: String? = null,
    @SerialName("rx_bytes") val rxBytes: PaddBytes? = null,
    @SerialName("tx_bytes") val txBytes: PaddBytes? = null,
)

@Serializable
data class PaddBytes(
    val value: Double,
    val unit: String,
)
