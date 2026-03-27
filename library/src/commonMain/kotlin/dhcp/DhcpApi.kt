package dev.jamieastley.kmpihole.api.dhcp

import dev.jamieastley.kmpihole.api.models.dhcp.DhcpLeasesResponse

interface DhcpApi {
    suspend fun getLeases(): DhcpLeasesResponse
    suspend fun deleteLease(ip: String)
}
