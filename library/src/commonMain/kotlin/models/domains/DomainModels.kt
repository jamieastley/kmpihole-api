package dev.jamieastley.kmpihole.api.models.domains

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class DomainType { Allow, Deny }
enum class DomainKind { Exact, Regex }

@Serializable
data class DomainsResponse(
    val domains: List<DomainObject> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class DomainObject(
    val domain: String,
    val unicode: String? = null,
    val type: String,
    val kind: String,
    val comment: String? = null,
    val groups: List<Int> = emptyList(),
    val enabled: Boolean,
    val id: Int,
    @SerialName("date_added") val dateAdded: Long,
    @SerialName("date_modified") val dateModified: Long,
)

@Serializable
data class DomainRequest(
    val domain: String,
    val comment: String? = null,
    val groups: List<Int>? = null,
    val enabled: Boolean = true,
)

@Serializable
data class BatchDeleteDomainsRequest(val items: List<BatchDeleteDomainItem>)

@Serializable
data class BatchDeleteDomainItem(
    val item: String,
    val type: String,
    val kind: String,
)
