package dev.jamieastley.kmpihole.api.config

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.config.ConfigResponse
import dev.jamieastley.kmpihole.api.models.config.PatchConfigRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonObject

internal class ConfigApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : ConfigApi {

    override suspend fun getConfig(): ConfigResponse =
        client.get("${config.baseUrl}/config").bodyOrThrow()

    override suspend fun patchConfig(request: PatchConfigRequest): ConfigResponse =
        client.patch("${config.baseUrl}/config") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun getConfigElement(element: String): JsonObject =
        client.get("${config.baseUrl}/config/$element").bodyOrThrow()

    override suspend fun addArrayItem(element: String, value: String) {
        val response = client.put("${config.baseUrl}/config/$element/$value")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun deleteArrayItem(element: String, value: String) {
        val response = client.delete("${config.baseUrl}/config/$element/$value")
        if (!response.status.isSuccess()) response.throwForStatus()
    }
}
