package dev.jamieastley.kmpihole.api.models.dns

import kotlinx.serialization.Serializable

@Serializable
data class BlockingStatusResponse(
    val blocking: String,
    val timer: Double? = null,
    val took: Double = 0.0,
)

@Serializable
data class SetBlockingRequest(
    val blocking: Boolean,
    val timer: Int? = null,
)
