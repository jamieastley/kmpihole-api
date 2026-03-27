package dev.jamieastley.kmpihole.api.action

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.action.ActionResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal class ActionApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : ActionApi {

    override suspend fun runGravity(): ActionResponse =
        client.post("${config.baseUrl}/action/gravity").bodyOrThrow()

    override suspend fun restartDns(): ActionResponse =
        client.post("${config.baseUrl}/action/restartdns").bodyOrThrow()

    override suspend fun flushLogs(): ActionResponse =
        client.post("${config.baseUrl}/action/flush/logs").bodyOrThrow()

    override suspend fun flushArp(): ActionResponse =
        client.post("${config.baseUrl}/action/flush/arp").bodyOrThrow()

    override suspend fun flushNetwork(): ActionResponse =
        client.post("${config.baseUrl}/action/flush/network").bodyOrThrow()
}
