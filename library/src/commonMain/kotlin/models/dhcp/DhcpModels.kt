package dev.jamieastley.kmpihole.api.models.dhcp

import kotlinx.serialization.Serializable

@Serializable
data class DhcpLeasesResponse(
    val leases: List<DhcpLease> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class DhcpLease(
    val expires: Long,
    val name: String? = null,
    val hwaddr: String,
    val ip: String,
    val clientid: String? = null,
)
