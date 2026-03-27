package dev.jamieastley.kmpihole.api.dns

import dev.jamieastley.kmpihole.api.models.dns.BlockingStatusResponse
import dev.jamieastley.kmpihole.api.models.dns.SetBlockingRequest

interface DnsApi {
    /** Get the current Pi-hole blocking status. */
    suspend fun getBlockingStatus(): BlockingStatusResponse

    /**
     * Enable or disable blocking. Optionally supply a [timer] in seconds after which the
     * opposite state will be automatically restored.
     */
    suspend fun setBlockingStatus(request: SetBlockingRequest): BlockingStatusResponse
}
