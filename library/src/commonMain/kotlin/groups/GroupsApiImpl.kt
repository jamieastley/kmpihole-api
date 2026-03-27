package dev.jamieastley.kmpihole.api.groups

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.internal.throwForStatus
import dev.jamieastley.kmpihole.api.models.groups.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class GroupsApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : GroupsApi {

    override suspend fun getGroups(): GroupsResponse =
        client.get("${config.baseUrl}/groups").bodyOrThrow()

    override suspend fun addGroup(request: GroupRequest): GroupsResponse =
        client.post("${config.baseUrl}/groups") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun replaceGroup(name: String, request: GroupRequest): GroupsResponse =
        client.put("${config.baseUrl}/groups/$name") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyOrThrow()

    override suspend fun deleteGroup(name: String) {
        val response = client.delete("${config.baseUrl}/groups/$name")
        if (!response.status.isSuccess()) response.throwForStatus()
    }

    override suspend fun batchDeleteGroups(request: BatchDeleteGroupsRequest) {
        val response = client.post("${config.baseUrl}/groups:batchDelete") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        if (!response.status.isSuccess()) response.throwForStatus()
    }
}
