package dev.jamieastley.kmpihole.api.models.queries

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueriesResponse(
    val queries: List<Query> = emptyList(),
    val cursor: Long? = null,
    val took: Double = 0.0,
)

@Serializable
data class Query(
    val id: Long,
    val time: Double,
    val type: String,
    val domain: String,
    val cname: String? = null,
    val status: String? = null,
    val client: QueryClient,
    val dnssec: String? = null,
    val reply: QueryReply? = null,
    @SerialName("list_id") val listId: Int? = null,
    val upstream: String? = null,
    val ede: ExtendedDnsError? = null,
)

@Serializable
data class QueryClient(
    val ip: String,
    val name: String? = null,
)

@Serializable
data class QueryReply(
    val type: String? = null,
    val time: Double,
)

@Serializable
data class ExtendedDnsError(
    val code: Int,
    val text: String? = null,
)

@Serializable
data class QuerySuggestionsResponse(
    val types: List<String> = emptyList(),
    val status: List<String> = emptyList(),
    val reply: List<String> = emptyList(),
    val dnssec: List<String> = emptyList(),
    val took: Double = 0.0,
)

data class QueryFilters(
    val from: Long? = null,
    val until: Long? = null,
    val length: Int? = null,
    val start: Int? = null,
    val cursor: Long? = null,
    val domain: String? = null,
    val client: String? = null,
    val upstream: String? = null,
    val type: String? = null,
    val status: String? = null,
    val reply: String? = null,
    val dnssec: String? = null,
)
