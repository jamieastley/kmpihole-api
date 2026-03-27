package dev.jamieastley.kmpihole.api.dns

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.PiHoleException
import dev.jamieastley.kmpihole.api.jsonResponse
import dev.jamieastley.kmpihole.api.mockHttpClient
import dev.jamieastley.kmpihole.api.models.dns.SetBlockingRequest
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class DnsApiTest {

    private val config = KmpiholeClientConfig(host = "pi.hole", useTls = false, port = 80)

    private val blockingEnabledJson = """
        {"blocking": "enabled", "timer": null, "took": 0.001}
    """.trimIndent()

    private val blockingDisabledJson = """
        {"blocking": "disabled", "timer": 300.0, "took": 0.001}
    """.trimIndent()

    @Test
    fun `getBlockingStatus returns enabled status`() = runTest {
        val client = mockHttpClient { jsonResponse(blockingEnabledJson) }
        val api = DnsApiImpl(client, config)

        val response = api.getBlockingStatus()

        assertEquals("enabled", response.blocking)
        assertNull(response.timer)
    }

    @Test
    fun `setBlockingStatus disables with timer`() = runTest {
        val client = mockHttpClient { jsonResponse(blockingDisabledJson) }
        val api = DnsApiImpl(client, config)

        val response = api.setBlockingStatus(SetBlockingRequest(blocking = false, timer = 300))

        assertEquals("disabled", response.blocking)
        assertEquals(300.0, response.timer)
    }

    @Test
    fun `getBlockingStatus throws Unauthorized on 401`() = runTest {
        val unauthorizedJson = """
            {"error": {"key": "unauthorized", "message": "Unauthorized", "hint": null}, "took": 0.001}
        """.trimIndent()
        val client = mockHttpClient { jsonResponse(unauthorizedJson, HttpStatusCode.Unauthorized) }
        val api = DnsApiImpl(client, config)

        assertFailsWith<PiHoleException.Unauthorized> {
            api.getBlockingStatus()
        }
    }
}
