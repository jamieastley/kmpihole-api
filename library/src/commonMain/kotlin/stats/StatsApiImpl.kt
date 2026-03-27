package dev.jamieastley.kmpihole.api.stats

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.stats.*
import io.ktor.client.*
import io.ktor.client.request.*

internal class StatsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : StatsApi {

    override suspend fun getSummary(): SummaryResponse =
        client.get("${config.baseUrl}/stats/summary").bodyOrThrow()

    override suspend fun getDatabaseSummary(from: Long, until: Long): DatabaseSummaryResponse =
        client.get("${config.baseUrl}/stats/database/summary") {
            parameter("from", from)
            parameter("until", until)
        }.bodyOrThrow()

    override suspend fun getUpstreams(): UpstreamsResponse =
        client.get("${config.baseUrl}/stats/upstreams").bodyOrThrow()

    override suspend fun getDatabaseUpstreams(from: Long, until: Long): UpstreamsResponse =
        client.get("${config.baseUrl}/stats/database/upstreams") {
            parameter("from", from)
            parameter("until", until)
        }.bodyOrThrow()

    override suspend fun getTopDomains(count: Int?, blocked: Boolean?): TopDomainsResponse =
        client.get("${config.baseUrl}/stats/top_domains") {
            count?.let { parameter("count", it) }
            blocked?.let { parameter("blocked", it) }
        }.bodyOrThrow()

    override suspend fun getDatabaseTopDomains(from: Long, until: Long, count: Int?, blocked: Boolean?): TopDomainsResponse =
        client.get("${config.baseUrl}/stats/database/top_domains") {
            parameter("from", from)
            parameter("until", until)
            count?.let { parameter("count", it) }
            blocked?.let { parameter("blocked", it) }
        }.bodyOrThrow()

    override suspend fun getTopClients(count: Int?, blocked: Boolean?): TopClientsResponse =
        client.get("${config.baseUrl}/stats/top_clients") {
            count?.let { parameter("count", it) }
            blocked?.let { parameter("blocked", it) }
        }.bodyOrThrow()

    override suspend fun getDatabaseTopClients(from: Long, until: Long, count: Int?, blocked: Boolean?): TopClientsResponse =
        client.get("${config.baseUrl}/stats/database/top_clients") {
            parameter("from", from)
            parameter("until", until)
            count?.let { parameter("count", it) }
            blocked?.let { parameter("blocked", it) }
        }.bodyOrThrow()

    override suspend fun getQueryTypes(): QueryTypesResponse =
        client.get("${config.baseUrl}/stats/query_types").bodyOrThrow()

    override suspend fun getDatabaseQueryTypes(from: Long, until: Long): QueryTypesResponse =
        client.get("${config.baseUrl}/stats/database/query_types") {
            parameter("from", from)
            parameter("until", until)
        }.bodyOrThrow()

    override suspend fun getRecentBlocked(): RecentBlockedResponse =
        client.get("${config.baseUrl}/stats/recent_blocked").bodyOrThrow()
}
