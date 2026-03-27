package dev.jamieastley.kmpihole.api.queries

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.queries.QueriesResponse
import dev.jamieastley.kmpihole.api.models.queries.QueryFilters
import dev.jamieastley.kmpihole.api.models.queries.QuerySuggestionsResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal class QueriesApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : QueriesApi {

    override suspend fun getQueries(filters: QueryFilters): QueriesResponse =
        client.get("${config.baseUrl}/queries") {
            filters.from?.let { parameter("from", it) }
            filters.until?.let { parameter("until", it) }
            filters.length?.let { parameter("length", it) }
            filters.start?.let { parameter("start", it) }
            filters.cursor?.let { parameter("cursor", it) }
            filters.domain?.let { parameter("domain", it) }
            filters.client?.let { parameter("client", it) }
            filters.upstream?.let { parameter("upstream", it) }
            filters.type?.let { parameter("type", it) }
            filters.status?.let { parameter("status", it) }
            filters.reply?.let { parameter("reply", it) }
            filters.dnssec?.let { parameter("dnssec", it) }
        }.bodyOrThrow()

    override suspend fun getQuerySuggestions(): QuerySuggestionsResponse =
        client.get("${config.baseUrl}/queries/suggestions").bodyOrThrow()
}
