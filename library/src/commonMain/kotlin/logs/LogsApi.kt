package dev.jamieastley.kmpihole.api.logs

import dev.jamieastley.kmpihole.api.models.logs.LogResponse

interface LogsApi {
    suspend fun getDnsmasqLog(nextId: Int? = null): LogResponse
    suspend fun getFtlLog(nextId: Int? = null): LogResponse
    suspend fun getWebserverLog(nextId: Int? = null): LogResponse
}
