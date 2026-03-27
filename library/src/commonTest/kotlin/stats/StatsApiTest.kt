package dev.jamieastley.kmpihole.api.stats

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.PiHoleException
import dev.jamieastley.kmpihole.api.jsonResponse
import dev.jamieastley.kmpihole.api.mockHttpClient
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class StatsApiTest {

    private val config = KmpiholeClientConfig(host = "pi.hole", useTls = false, port = 80)

    private val summaryJson = """
        {
          "queries": {
            "total": 7497,
            "blocked": 3465,
            "percent_blocked": 46.2,
            "unique_domains": 445,
            "forwarded": 4574,
            "cached": 9765,
            "frequency": 1.1,
            "types": {"A": 3643, "AAAA": 123},
            "status": {"GRAVITY": 72, "FORWARDED": 533},
            "replies": {"NODATA": 72}
          },
          "clients": {"total": 10, "active": 5},
          "gravity": {"domains_being_blocked": 225382},
          "took": 0.003
        }
    """.trimIndent()

    @Test
    fun `getSummary parses response correctly`() = runTest {
        val client = mockHttpClient { jsonResponse(summaryJson) }
        val api = StatsApiImpl(client, config)

        val response = api.getSummary()

        assertEquals(7497, response.queries.total)
        assertEquals(3465, response.queries.blocked)
        assertEquals(46.2, response.queries.percentBlocked)
        assertEquals(10, response.clients.total)
        assertEquals(225382, response.gravity.domainsBeingBlocked)
        assertEquals(0.003, response.took)
    }

    @Test
    fun `getSummary throws Unauthorized on 401`() = runTest {
        val errorJson = """
            {"error": {"key": "unauthorized", "message": "Unauthorized", "hint": null}}
        """.trimIndent()
        val client = mockHttpClient { jsonResponse(errorJson, HttpStatusCode.Unauthorized) }
        val api = StatsApiImpl(client, config)

        assertFailsWith<PiHoleException.Unauthorized> { api.getSummary() }
    }

    @Test
    fun `getTopDomains parses response`() = runTest {
        val json = """
            {
              "top_domains": [{"domain": "example.com", "count": 100}],
              "top_blocked": [{"domain": "ads.example.com", "count": 50}],
              "total_queries": 7497,
              "blocked_queries": 3465,
              "took": 0.002
            }
        """.trimIndent()
        val client = mockHttpClient { jsonResponse(json) }
        val api = StatsApiImpl(client, config)

        val response = api.getTopDomains()

        assertEquals(1, response.topDomains.size)
        assertEquals("example.com", response.topDomains[0].domain)
        assertEquals(100, response.topDomains[0].count)
    }

    @Test
    fun `getRecentBlocked parses response`() = runTest {
        val json = """{"blocked": ["ads.tracker.com"], "took": 0.001}"""
        val client = mockHttpClient { jsonResponse(json) }
        val api = StatsApiImpl(client, config)

        val response = api.getRecentBlocked()

        assertEquals(listOf("ads.tracker.com"), response.blocked)
    }
}
