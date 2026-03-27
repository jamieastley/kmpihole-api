package dev.jamieastley.kmpihole.api.history

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.bodyOrThrow
import dev.jamieastley.kmpihole.api.models.history.ClientHistoryResponse
import dev.jamieastley.kmpihole.api.models.history.HistoryResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal class HistoryApiImpl(
    private val client: HttpClient,
    private val config: KmpiholeClientConfig,
) : HistoryApi {

    override suspend fun getHistory(): HistoryResponse =
        client.get("${config.baseUrl}/history").bodyOrThrow()

    override suspend fun getHistoryClients(): ClientHistoryResponse =
        client.get("${config.baseUrl}/history/clients").bodyOrThrow()

    override suspend fun getDatabaseHistory(from: Long, until: Long): HistoryResponse =
        client.get("${config.baseUrl}/history/database") {
            parameter("from", from)
            parameter("until", until)
        }.bodyOrThrow()

    override suspend fun getDatabaseHistoryClients(from: Long, until: Long): ClientHistoryResponse =
        client.get("${config.baseUrl}/history/database/clients") {
            parameter("from", from)
            parameter("until", until)
        }.bodyOrThrow()
}
