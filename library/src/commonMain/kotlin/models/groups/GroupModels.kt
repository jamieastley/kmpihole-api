package dev.jamieastley.kmpihole.api.models.groups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupsResponse(
    val groups: List<Group> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class Group(
    val id: Int,
    val name: String,
    val comment: String? = null,
    val enabled: Boolean,
    @SerialName("date_added") val dateAdded: Long,
    @SerialName("date_modified") val dateModified: Long,
)

@Serializable
data class GroupRequest(
    val name: String,
    val comment: String? = null,
    val enabled: Boolean = true,
)

@Serializable
data class BatchDeleteGroupsRequest(val items: List<BatchDeleteGroupItem>)

@Serializable
data class BatchDeleteGroupItem(val item: String)
