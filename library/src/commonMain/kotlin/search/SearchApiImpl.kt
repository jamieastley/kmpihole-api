package dev.jamieastley.kmpihole.api.search

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.search.SearchResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal class SearchApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : SearchApi {

    override suspend fun search(domain: String): SearchResponse =
        client.get("${config.baseUrl}/search/$domain").bodyOrThrow()
}
