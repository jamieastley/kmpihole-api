package dev.jamieastley.kmpihole.api.padd

import dev.jamieastley.kmpihole.api.models.padd.PaddResponse

interface PaddApi {
    suspend fun getPadd(): PaddResponse
}
