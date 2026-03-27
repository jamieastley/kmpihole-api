package dev.jamieastley.kmpihole.api.config

import dev.jamieastley.kmpihole.api.models.config.ConfigResponse
import dev.jamieastley.kmpihole.api.models.config.PatchConfigRequest
import kotlinx.serialization.json.JsonObject

interface ConfigApi {
    /** Get the full Pi-hole configuration tree. */
    suspend fun getConfig(): ConfigResponse

    /** Partially update the configuration. Only the provided keys are modified. */
    suspend fun patchConfig(request: PatchConfigRequest): ConfigResponse

    /** Get a specific element from the configuration tree by dot-separated [element] path. */
    suspend fun getConfigElement(element: String): JsonObject

    /** Add a value to an array config element. */
    suspend fun addArrayItem(element: String, value: String)

    /** Delete a value from an array config element. */
    suspend fun deleteArrayItem(element: String, value: String)
}
