package dev.jamieastley.kmpihole.api.info

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.info.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class InfoApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : InfoApi {

    override suspend fun getClientInfo(): ClientInfoResponse =
        client.get("${config.baseUrl}/info/client").bodyOrThrow()

    override suspend fun getSystemInfo(): SystemInfoResponse =
        client.get("${config.baseUrl}/info/system").bodyOrThrow()

    override suspend fun getDatabaseInfo(): DbInfoResponse =
        client.get("${config.baseUrl}/info/database").bodyOrThrow()

    override suspend fun getFtlInfo(): FtlInfoResponse =
        client.get("${config.baseUrl}/info/ftl").bodyOrThrow()

    override suspend fun getHostInfo(): HostInfoResponse =
        client.get("${config.baseUrl}/info/host").bodyOrThrow()

    override suspend fun getSensors(): SensorsResponse =
        client.get("${config.baseUrl}/info/sensors").bodyOrThrow()

    override suspend fun getVersion(): VersionResponse =
        client.get("${config.baseUrl}/info/version").bodyOrThrow()

    override suspend fun getMessages(): MessagesResponse =
        client.get("${config.baseUrl}/info/messages").bodyOrThrow()

    override suspend fun deleteMessage(messageId: Int) {
        val response = client.delete("${config.baseUrl}/info/messages/$messageId")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun getMessageCount(): MessageCountResponse =
        client.get("${config.baseUrl}/info/messages/count").bodyOrThrow()

    override suspend fun getMetricsInfo(): MetricsInfoResponse =
        client.get("${config.baseUrl}/info/metrics").bodyOrThrow()

    override suspend fun getLoginInfo(): LoginInfoResponse =
        client.get("${config.baseUrl}/info/login").bodyOrThrow()
}
