package dev.jamieastley.kmpihole.api.clients

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.clients.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class ClientsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : ClientsApi {

    override suspend fun getClients(): ClientsResponse =
        client.get("${config.baseUrl}/clients").bodyOrThrow()

    override suspend fun addClient(request: ClientRequest): ClientsResponse =
        client.post("${config.baseUrl}/clients") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun replaceClient(client: String, request: ClientRequest): ClientsResponse =
        this.client.put("${config.baseUrl}/clients/$client") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun deleteClient(client: String) {
        val response = this.client.delete("${config.baseUrl}/clients/$client")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun batchDeleteClients(request: BatchDeleteClientsRequest) {
        val response = client.post("${config.baseUrl}/clients:batchDelete") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun getClientSuggestions(): ClientSuggestionsResponse =
        client.get("${config.baseUrl}/clients/_suggestions").bodyOrThrow()
}
