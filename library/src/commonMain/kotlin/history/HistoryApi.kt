package dev.jamieastley.kmpihole.api.history

import dev.jamieastley.kmpihole.api.models.history.ClientHistoryResponse
import dev.jamieastley.kmpihole.api.models.history.HistoryResponse

interface HistoryApi {
    /** Get activity graph data (recent data). */
    suspend fun getHistory(): HistoryResponse

    /** Get per-client activity graph data (recent data). */
    suspend fun getHistoryClients(): ClientHistoryResponse

    /** Get activity graph data from the long-term database. */
    suspend fun getDatabaseHistory(from: Long, until: Long): HistoryResponse

    /** Get per-client activity graph data from the long-term database. */
    suspend fun getDatabaseHistoryClients(from: Long, until: Long): ClientHistoryResponse
}
