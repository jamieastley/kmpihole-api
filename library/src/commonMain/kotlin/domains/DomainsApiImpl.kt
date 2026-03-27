package dev.jamieastley.kmpihole.api.domains

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.domains.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class DomainsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : DomainsApi {

    override suspend fun getDomains(): DomainsResponse =
        client.get("${config.baseUrl}/domains").bodyOrThrow()

    override suspend fun getDomainsByType(type: DomainType): DomainsResponse =
        client.get("${config.baseUrl}/domains/${type.name}").bodyOrThrow()

    override suspend fun getDomainsByTypeAndKind(type: DomainType, kind: DomainKind): DomainsResponse =
        client.get("${config.baseUrl}/domains/${type.name}/${kind.name}").bodyOrThrow()

    override suspend fun getDomain(type: DomainType, kind: DomainKind, domain: String): DomainsResponse =
        client.get("${config.baseUrl}/domains/${type.name}/${kind.name}/$domain").bodyOrThrow()

    override suspend fun addDomain(type: DomainType, kind: DomainKind, request: DomainRequest): DomainsResponse =
        client.post("${config.baseUrl}/domains/${type.name}/${kind.name}") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun replaceDomain(type: DomainType, kind: DomainKind, domain: String, request: DomainRequest): DomainsResponse =
        client.put("${config.baseUrl}/domains/${type.name}/${kind.name}/$domain") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun deleteDomain(type: DomainType, kind: DomainKind, domain: String) {
        val response = client.delete("${config.baseUrl}/domains/${type.name}/${kind.name}/$domain")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun batchDeleteDomains(request: BatchDeleteDomainsRequest) {
        val response = client.post("${config.baseUrl}/domains:batchDelete") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        if (!response.status.isSuccess()) response.throwForStatus()
    }
}
