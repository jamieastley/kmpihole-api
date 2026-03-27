package dev.jamieastley.kmpihole.api.dhcp

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.dhcp.DhcpLeasesResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class DhcpApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : DhcpApi {

    override suspend fun getLeases(): DhcpLeasesResponse =
        client.get("${config.baseUrl}/dhcp/leases").bodyOrThrow()

    override suspend fun deleteLease(ip: String) {
        val response = client.delete("${config.baseUrl}/dhcp/leases/$ip")
        if (!response.status.isSuccess()) response.throwForStatus()
    }
}
