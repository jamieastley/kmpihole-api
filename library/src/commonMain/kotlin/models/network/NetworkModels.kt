package dev.jamieastley.kmpihole.api.models.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ─── Devices ──────────────────────────────────────────────────────────────────

@Serializable
data class NetworkDevicesResponse(
    val devices: List<NetworkDevice> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class NetworkDevice(
    val id: Int,
    val hwaddr: String,
    @SerialName("macVendor") val macVendor: String? = null,
    @SerialName("interface") val networkInterface: String? = null,
    val ips: List<IpEntry> = emptyList(),
    val name: List<String> = emptyList(),
    @SerialName("last_seen") val lastSeen: Long? = null,
    @SerialName("firstSeen") val firstSeen: Long? = null,
    @SerialName("numQueries") val numQueries: Int? = null,
)

@Serializable
data class IpEntry(
    val ip: String,
    val name: String? = null,
    @SerialName("last_seen") val lastSeen: Long? = null,
)

// ─── Gateway ──────────────────────────────────────────────────────────────────

@Serializable
data class GatewayResponse(
    val gateway: List<GatewayEntry> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class GatewayEntry(
    val family: String,
    @SerialName("interface") val networkInterface: String,
    val address: String,
    val local: List<String> = emptyList(),
)

// ─── Routes ───────────────────────────────────────────────────────────────────

@Serializable
data class RoutesResponse(
    val routes: List<Route> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class Route(
    val gateway: String? = null,
    val family: String,
    val table: Int? = null,
    val protocol: String? = null,
    val scope: String? = null,
    val type: String? = null,
    val flags: List<String> = emptyList(),
    val oif: String? = null,
    val iif: String? = null,
    val dst: String? = null,
    val src: String? = null,
    val prefsrc: String? = null,
    val priority: Int? = null,
    val pref: Int? = null,
)

// ─── Interfaces ───────────────────────────────────────────────────────────────

@Serializable
data class InterfacesResponse(
    val interfaces: List<NetworkInterface> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class NetworkInterface(
    val name: String,
    val speed: Int? = null,
    val carrier: Boolean? = null,
    val type: String? = null,
    val addresses: List<InterfaceAddress> = emptyList(),
)

@Serializable
data class InterfaceAddress(
    val address: String,
    val family: String? = null,
    val netmask: String? = null,
)
