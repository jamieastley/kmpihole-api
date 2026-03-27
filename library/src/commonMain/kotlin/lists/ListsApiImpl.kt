package dev.jamieastley.kmpihole.api.lists

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.lists.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class ListsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : ListsApi {

    override suspend fun getLists(): ListsResponse =
        client.get("${config.baseUrl}/lists").bodyOrThrow()

    override suspend fun addList(request: ListRequest): ListsResponse =
        client.post("${config.baseUrl}/lists") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun replaceList(listId: String, request: ListRequest): ListsResponse =
        client.put("${config.baseUrl}/lists/$listId") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun deleteList(listId: String) {
        val response = client.delete("${config.baseUrl}/lists/$listId")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun batchDeleteLists(request: BatchDeleteListsRequest) {
        val response = client.post("${config.baseUrl}/lists:batchDelete") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        if (!response.status.isSuccess()) response.throwForStatus()
    }
}
