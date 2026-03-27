package dev.jamieastley.kmpihole.api.dns

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.dns.BlockingStatusResponse
import dev.jamieastley.kmpihole.api.models.dns.SetBlockingRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class DnsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : DnsApi {

    override suspend fun getBlockingStatus(): BlockingStatusResponse =
        client.get("${config.baseUrl}/dns/blocking").bodyOrThrow()

    override suspend fun setBlockingStatus(request: SetBlockingRequest): BlockingStatusResponse =
        client.post("${config.baseUrl}/dns/blocking") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()
}
