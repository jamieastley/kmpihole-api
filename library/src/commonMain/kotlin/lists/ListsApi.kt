package dev.jamieastley.kmpihole.api.lists

import dev.jamieastley.kmpihole.api.models.lists.*

interface ListsApi {
    suspend fun getLists(): ListsResponse
    suspend fun addList(request: ListRequest): ListsResponse
    suspend fun replaceList(listId: String, request: ListRequest): ListsResponse
    suspend fun deleteList(listId: String)
    suspend fun batchDeleteLists(request: BatchDeleteListsRequest)
}
