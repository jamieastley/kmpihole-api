package dev.jamieastley.kmpihole.api.domains

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.PiHoleException
import dev.jamieastley.kmpihole.api.jsonResponse
import dev.jamieastley.kmpihole.api.mockHttpClient
import dev.jamieastley.kmpihole.api.models.domains.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DomainsApiTest {

    private val config = KmpiholeClientConfig(host = "pi.hole", useTls = false, port = 80)

    private val domainsJson = """
        {
          "domains": [
            {
              "domain": "example.com",
              "unicode": "example.com",
              "type": "allow",
              "kind": "exact",
              "comment": "test",
              "groups": [0],
              "enabled": true,
              "id": 1,
              "date_added": 1664624500,
              "date_modified": 1664624500
            }
          ],
          "took": 0.002
        }
    """.trimIndent()

    @Test
    fun `getDomains returns list of domains`() = runTest {
        val client = mockHttpClient { jsonResponse(domainsJson) }
        val api = DomainsApiImpl(client, config)

        val response = api.getDomains()

        assertEquals(1, response.domains.size)
        assertEquals("example.com", response.domains[0].domain)
        assertEquals("allow", response.domains[0].type)
        assertEquals("exact", response.domains[0].kind)
    }

    @Test
    fun `addDomain returns updated domains list`() = runTest {
        val client = mockHttpClient { jsonResponse(domainsJson, HttpStatusCode.Created) }
        val api = DomainsApiImpl(client, config)

        val response = api.addDomain(
            DomainType.Allow,
            DomainKind.Exact,
            DomainRequest(domain = "example.com", comment = "test"),
        )

        assertEquals(1, response.domains.size)
    }

    @Test
    fun `deleteDomain succeeds on 204`() = runTest {
        val client = mockHttpClient { jsonResponse("", HttpStatusCode.NoContent) }
        val api = DomainsApiImpl(client, config)

        api.deleteDomain(DomainType.Allow, DomainKind.Exact, "example.com")
    }

    @Test
    fun `getDomains throws Unauthorized on 401`() = runTest {
        val errorJson = """{"error": {"key": "unauthorized", "message": "Unauthorized", "hint": null}}"""
        val client = mockHttpClient { jsonResponse(errorJson, HttpStatusCode.Unauthorized) }
        val api = DomainsApiImpl(client, config)

        assertFailsWith<PiHoleException.Unauthorized> { api.getDomains() }
    }
}
