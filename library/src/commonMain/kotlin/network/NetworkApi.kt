package dev.jamieastley.kmpihole.api.network

import dev.jamieastley.kmpihole.api.models.network.*

interface NetworkApi {
    suspend fun getDevices(): NetworkDevicesResponse
    suspend fun deleteDevice(deviceId: Int)
    suspend fun getGateway(): GatewayResponse
    suspend fun getRoutes(): RoutesResponse
    suspend fun getInterfaces(): InterfacesResponse
}
