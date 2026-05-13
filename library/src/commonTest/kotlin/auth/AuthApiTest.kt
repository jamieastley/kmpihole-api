package dev.jamieastley.kmpihole.api.auth

import dev.jamieastley.kmpihole.api.KmpiholeClient
import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.PiHoleException
import dev.jamieastley.kmpihole.api.internal.SessionManager
import dev.jamieastley.kmpihole.api.jsonResponse
import dev.jamieastley.kmpihole.api.mockEngine
import dev.jamieastley.kmpihole.api.mockHttpClient
import dev.jamieastley.kmpihole.api.models.auth.AuthRequest
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class AuthApiTest {

    private val config = KmpiholeClientConfig(host = "pi.hole", useTls = false, port = 80)

    private val loginSuccessJson = """
        {
          "session": {
            "valid": true,
            "totp": false,
            "sid": "test-sid-123",
            "csrf": "csrf-token",
            "validity": 1800,
            "message": "Login successful"
          },
          "took": 0.001
        }
    """.trimIndent()

    private val unauthorizedJson = """
        {
          "error": {
            "key": "unauthorized",
            "message": "Unauthorized",
            "hint": null
          },
          "took": 0.001
        }
    """.trimIndent()

    @Test
    fun `login stores sid in session manager`() = runTest {
        val engine = mockEngine { jsonResponse(loginSuccessJson) }
        val client = KmpiholeClient(config, engine)

        client.auth.login(AuthRequest(password = "secret"))
        client.auth.checkAuth()

        assertEquals("test-sid-123", engine.requestHistory.last().headers["sid"])
    }

    @Test
    fun `checkAuth returns session response`() = runTest {
        val client = KmpiholeClient(config, mockEngine { jsonResponse(loginSuccessJson) })

        val response = client.auth.checkAuth()

        assertEquals(true, response.session.valid)
        assertEquals("test-sid-123", response.session.sid)
    }

    @Test
    fun `logout clears session manager`() = runTest {
        val engine = mockEngine { jsonResponse(loginSuccessJson) }
        val client = KmpiholeClient(config, engine)

        client.auth.login(AuthRequest(password = "secret"))
        client.auth.logout()
        client.auth.checkAuth()

        assertNull(engine.requestHistory.last().headers["sid"])
    }

    @Test
    fun `login throws Unauthorized on 401`() = runTest {
        val client = KmpiholeClient(
            config,
            mockEngine { jsonResponse(unauthorizedJson, HttpStatusCode.Unauthorized) })

        assertFailsWith<PiHoleException.Unauthorized> {
            client.auth.login(AuthRequest(password = "wrong"))
        }
    }

    @Test
    fun `checkAuth response contains totp flag`() = runTest {
        val totpJson = """
            {
              "session": {
                "valid": false,
                "totp": true,
                "sid": null,
                "csrf": null,
                "validity": 0,
                "message": "2FA required"
              },
              "took": 0.001
            }
        """.trimIndent()
        val client = KmpiholeClient(
            config,
            mockEngine { jsonResponse(totpJson, HttpStatusCode.Unauthorized) })

        assertFailsWith<PiHoleException.Unauthorized> {
            client.auth.checkAuth()
        }
    }
}
