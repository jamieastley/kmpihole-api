package dev.jamieastley.kmpihole.api.models.lists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListsResponse(
    val lists: List<PiHoleList> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class PiHoleList(
    val id: Int,
    val address: String,
    val type: String,
    val comment: String? = null,
    val groups: List<Int> = emptyList(),
    val enabled: Boolean,
    @SerialName("date_added") val dateAdded: Long,
    @SerialName("date_modified") val dateModified: Long,
    @SerialName("date_updated") val dateUpdated: Long? = null,
    val number: Int? = null,
    @SerialName("invalid_domains") val invalidDomains: Int? = null,
    @SerialName("abp_entries") val abpEntries: Int? = null,
    val status: Int? = null,
)

@Serializable
data class ListRequest(
    val address: String,
    val type: String,
    val comment: String? = null,
    val groups: List<Int>? = null,
    val enabled: Boolean = true,
)

@Serializable
data class BatchDeleteListsRequest(val items: List<BatchDeleteListItem>)

@Serializable
data class BatchDeleteListItem(val item: String)
