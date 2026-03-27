package dev.jamieastley.kmpihole.api.network

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.network.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class NetworkApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : NetworkApi {

    override suspend fun getDevices(): NetworkDevicesResponse =
        client.get("${config.baseUrl}/network/devices").bodyOrThrow()

    override suspend fun deleteDevice(deviceId: Int) {
        val response = client.delete("${config.baseUrl}/network/devices/$deviceId")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun getGateway(): GatewayResponse =
        client.get("${config.baseUrl}/network/gateway").bodyOrThrow()

    override suspend fun getRoutes(): RoutesResponse =
        client.get("${config.baseUrl}/network/routes").bodyOrThrow()

    override suspend fun getInterfaces(): InterfacesResponse =
        client.get("${config.baseUrl}/network/interfaces").bodyOrThrow()
}
