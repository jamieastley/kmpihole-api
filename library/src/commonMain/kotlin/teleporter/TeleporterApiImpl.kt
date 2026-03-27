package dev.jamieastley.kmpihole.api.teleporter

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.teleporter.TeleporterImportResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal class TeleporterApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : TeleporterApi {

    override suspend fun exportSettings(): ByteArray =
        client.get("${config.baseUrl}/teleporter").readRawBytes()

    override suspend fun importSettings(data: ByteArray, filename: String): TeleporterImportResponse =
        client.submitFormWithBinaryData(
            url = "${config.baseUrl}/teleporter",
            formData = formData {
                append("file", data, Headers.build {
                    append(HttpHeaders.ContentType, "application/zip")
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
            }
        ).bodyOrThrow()
}
