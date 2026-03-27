package dev.jamieastley.kmpihole.api.action

import dev.jamieastley.kmpihole.api.models.action.ActionResponse

interface ActionApi {
    suspend fun runGravity(): ActionResponse
    suspend fun restartDns(): ActionResponse
    suspend fun flushLogs(): ActionResponse
    suspend fun flushArp(): ActionResponse
    suspend fun flushNetwork(): ActionResponse
}
