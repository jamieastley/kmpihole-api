package dev.jamieastley.kmpihole.api.groups

import dev.jamieastley.kmpihole.api.models.groups.*

interface GroupsApi {
    suspend fun getGroups(): GroupsResponse
    suspend fun addGroup(request: GroupRequest): GroupsResponse
    suspend fun replaceGroup(name: String, request: GroupRequest): GroupsResponse
    suspend fun deleteGroup(name: String)
    suspend fun batchDeleteGroups(request: BatchDeleteGroupsRequest)
}
