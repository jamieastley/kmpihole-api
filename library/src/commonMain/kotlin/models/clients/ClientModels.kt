package dev.jamieastley.kmpihole.api.models.clients

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClientsResponse(
    val clients: List<Client> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class Client(
    val id: Int,
    val client: String,
    val name: String? = null,
    val comment: String? = null,
    val groups: List<Int> = emptyList(),
    @SerialName("date_added") val dateAdded: Long,
    @SerialName("date_modified") val dateModified: Long,
)

@Serializable
data class ClientRequest(
    val client: String,
    val comment: String? = null,
    val groups: List<Int>? = null,
)

@Serializable
data class ClientSuggestionsResponse(
    val clients: List<String> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class BatchDeleteClientsRequest(val items: List<BatchDeleteClientItem>)

@Serializable
data class BatchDeleteClientItem(val item: String)
