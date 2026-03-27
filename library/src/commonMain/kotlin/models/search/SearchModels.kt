package dev.jamieastley.kmpihole.api.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val search: SearchResults,
    val took: Double = 0.0,
)

@Serializable
data class SearchResults(
    val domains: List<SearchDomainResult> = emptyList(),
    val gravity: List<SearchGravityResult> = emptyList(),
)

@Serializable
data class SearchDomainResult(
    val domain: String,
    val comment: String? = null,
    val enabled: Boolean,
    val type: String,
    val kind: String,
    val id: Int,
    @SerialName("date_added") val dateAdded: Long,
    @SerialName("date_modified") val dateModified: Long,
    val groups: List<Int> = emptyList(),
)

@Serializable
data class SearchGravityResult(
    val domain: String,
    val address: String,
    val comment: String? = null,
    val enabled: Boolean? = null,
    val id: Int? = null,
    @SerialName("date_added") val dateAdded: Long? = null,
    val groups: List<Int> = emptyList(),
)
