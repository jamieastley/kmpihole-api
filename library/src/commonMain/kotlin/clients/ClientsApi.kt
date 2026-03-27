package dev.jamieastley.kmpihole.api.clients

import dev.jamieastley.kmpihole.api.models.clients.*

interface ClientsApi {
    suspend fun getClients(): ClientsResponse
    suspend fun addClient(request: ClientRequest): ClientsResponse
    suspend fun replaceClient(client: String, request: ClientRequest): ClientsResponse
    suspend fun deleteClient(client: String)
    suspend fun batchDeleteClients(request: BatchDeleteClientsRequest)
    suspend fun getClientSuggestions(): ClientSuggestionsResponse
}
