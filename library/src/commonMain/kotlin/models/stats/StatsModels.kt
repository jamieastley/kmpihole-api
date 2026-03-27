package dev.jamieastley.kmpihole.api.models.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ─── Summary ─────────────────────────────────────────────────────────────────

@Serializable
data class SummaryResponse(
    val queries: QueryStats,
    val clients: ClientStats,
    val gravity: GravityStats,
    val took: Double = 0.0,
)

@Serializable
data class QueryStats(
    val total: Int,
    val blocked: Int,
    @SerialName("percent_blocked") val percentBlocked: Double,
    @SerialName("unique_domains") val uniqueDomains: Int,
    val forwarded: Int,
    val cached: Int,
    val frequency: Double,
    val types: Map<String, Int> = emptyMap(),
    val status: Map<String, Int> = emptyMap(),
    val replies: Map<String, Int> = emptyMap(),
)

@Serializable
data class ClientStats(
    val total: Int,
    val active: Int,
)

@Serializable
data class GravityStats(
    @SerialName("domains_being_blocked") val domainsBeingBlocked: Int,
    @SerialName("last_update") val lastUpdate: Long? = null,
)

// ─── Database Summary ─────────────────────────────────────────────────────────

@Serializable
data class DatabaseSummaryResponse(
    val queries: DatabaseQueryStats,
    val took: Double = 0.0,
)

@Serializable
data class DatabaseQueryStats(
    val total: Int,
    val blocked: Int,
    @SerialName("percent_blocked") val percentBlocked: Double,
    @SerialName("unique_domains") val uniqueDomains: Int? = null,
)

// ─── Upstreams ────────────────────────────────────────────────────────────────

@Serializable
data class UpstreamsResponse(
    val upstreams: List<Upstream>,
    @SerialName("forwarded_queries") val forwardedQueries: Int? = null,
    @SerialName("total_queries") val totalQueries: Int? = null,
    val took: Double = 0.0,
)

@Serializable
data class Upstream(
    val ip: String,
    val port: Int? = null,
    val name: String? = null,
    val count: Int,
    @SerialName("statistics") val statistics: UpstreamStats? = null,
)

@Serializable
data class UpstreamStats(
    val response: UpstreamResponseStats? = null,
)

@Serializable
data class UpstreamResponseStats(
    val min: Double? = null,
    val max: Double? = null,
    val avg: Double? = null,
    val var_: Double? = null,
)

// ─── Top Domains ──────────────────────────────────────────────────────────────

@Serializable
data class TopDomainsResponse(
    @SerialName("top_domains") val topDomains: List<TopDomain> = emptyList(),
    @SerialName("top_blocked") val topBlocked: List<TopDomain> = emptyList(),
    @SerialName("total_queries") val totalQueries: Int? = null,
    @SerialName("blocked_queries") val blockedQueries: Int? = null,
    val took: Double = 0.0,
)

@Serializable
data class TopDomain(
    val domain: String,
    val count: Int,
)

// ─── Top Clients ──────────────────────────────────────────────────────────────

@Serializable
data class TopClientsResponse(
    @SerialName("top_sources") val topSources: List<TopClient> = emptyList(),
    @SerialName("top_sources_blocked") val topSourcesBlocked: List<TopClient> = emptyList(),
    @SerialName("total_queries") val totalQueries: Int? = null,
    @SerialName("blocked_queries") val blockedQueries: Int? = null,
    val took: Double = 0.0,
)

@Serializable
data class TopClient(
    val ip: String,
    val name: String? = null,
    val count: Int,
)

// ─── Query Types ──────────────────────────────────────────────────────────────

@Serializable
data class QueryTypesResponse(
    val types: Map<String, Double> = emptyMap(),
    val took: Double = 0.0,
)

// ─── Recent Blocked ───────────────────────────────────────────────────────────

@Serializable
data class RecentBlockedResponse(
    val blocked: List<String>,
    val took: Double = 0.0,
)
