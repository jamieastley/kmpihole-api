package dev.jamieastley.kmpihole.api.info

import dev.jamieastley.kmpihole.api.models.info.*

interface InfoApi {
    suspend fun getClientInfo(): ClientInfoResponse
    suspend fun getSystemInfo(): SystemInfoResponse
    suspend fun getDatabaseInfo(): DbInfoResponse
    suspend fun getFtlInfo(): FtlInfoResponse
    suspend fun getHostInfo(): HostInfoResponse
    suspend fun getSensors(): SensorsResponse
    suspend fun getVersion(): VersionResponse
    suspend fun getMessages(): MessagesResponse
    suspend fun deleteMessage(messageId: Int)
    suspend fun getMessageCount(): MessageCountResponse
    suspend fun getMetricsInfo(): MetricsInfoResponse
    suspend fun getLoginInfo(): LoginInfoResponse
}
