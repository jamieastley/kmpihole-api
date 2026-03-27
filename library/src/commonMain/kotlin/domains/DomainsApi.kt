package dev.jamieastley.kmpihole.api.domains

import dev.jamieastley.kmpihole.api.models.domains.*

interface DomainsApi {
    /** Get all domains. */
    suspend fun getDomains(): DomainsResponse

    /** Get domains filtered by [type] (allow/deny). */
    suspend fun getDomainsByType(type: DomainType): DomainsResponse

    /** Get domains filtered by [type] and [kind] (exact/regex). */
    suspend fun getDomainsByTypeAndKind(type: DomainType, kind: DomainKind): DomainsResponse

    /** Get a specific domain by [type], [kind], and exact [domain] string. */
    suspend fun getDomain(type: DomainType, kind: DomainKind, domain: String): DomainsResponse

    /** Add a new domain of the given [type] and [kind]. */
    suspend fun addDomain(type: DomainType, kind: DomainKind, request: DomainRequest): DomainsResponse

    /** Replace (update) an existing domain. */
    suspend fun replaceDomain(type: DomainType, kind: DomainKind, domain: String, request: DomainRequest): DomainsResponse

    /** Delete a specific domain. */
    suspend fun deleteDomain(type: DomainType, kind: DomainKind, domain: String)

    /** Delete multiple domains in one request. */
    suspend fun batchDeleteDomains(request: BatchDeleteDomainsRequest)
}
