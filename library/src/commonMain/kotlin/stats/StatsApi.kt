package dev.jamieastley.kmpihole.api.stats

import dev.jamieastley.kmpihole.api.models.stats.*

interface StatsApi {
    suspend fun getSummary(): SummaryResponse
    suspend fun getDatabaseSummary(from: Long, until: Long): DatabaseSummaryResponse
    suspend fun getUpstreams(): UpstreamsResponse
    suspend fun getDatabaseUpstreams(from: Long, until: Long): UpstreamsResponse
    suspend fun getTopDomains(count: Int? = null, blocked: Boolean? = null): TopDomainsResponse
    suspend fun getDatabaseTopDomains(from: Long, until: Long, count: Int? = null, blocked: Boolean? = null): TopDomainsResponse
    suspend fun getTopClients(count: Int? = null, blocked: Boolean? = null): TopClientsResponse
    suspend fun getDatabaseTopClients(from: Long, until: Long, count: Int? = null, blocked: Boolean? = null): TopClientsResponse
    suspend fun getQueryTypes(): QueryTypesResponse
    suspend fun getDatabaseQueryTypes(from: Long, until: Long): QueryTypesResponse
    suspend fun getRecentBlocked(): RecentBlockedResponse
}
