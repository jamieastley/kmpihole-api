package dev.jamieastley.kmpihole.api.padd

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.padd.PaddResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal class PaddApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : PaddApi {

    override suspend fun getPadd(): PaddResponse =
        client.get("${config.baseUrl}/padd").bodyOrThrow()
}
