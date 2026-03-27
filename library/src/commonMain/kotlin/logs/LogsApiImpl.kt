package dev.jamieastley.kmpihole.api.logs

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.logs.LogResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal class LogsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : LogsApi {

    override suspend fun getDnsmasqLog(nextId: Int?): LogResponse =
        client.get("${config.baseUrl}/logs/dnsmasq") {
            nextId?.let { parameter("nextID", it) }
        }.bodyOrThrow()

    override suspend fun getFtlLog(nextId: Int?): LogResponse =
        client.get("${config.baseUrl}/logs/ftl") {
            nextId?.let { parameter("nextID", it) }
        }.bodyOrThrow()

    override suspend fun getWebserverLog(nextId: Int?): LogResponse =
        client.get("${config.baseUrl}/logs/webserver") {
            nextId?.let { parameter("nextID", it) }
        }.bodyOrThrow()
}
