package dev.jamieastley.kmpihole.api.auth

import dev.jamieastley.kmpihole.api.KmpiholeClientConfig
import dev.jamieastley.kmpihole.api.internal.PiHoleException
import dev.jamieastley.kmpihole.api.internal.SessionManager
import dev.jamieastley.kmpihole.api.jsonResponse
import dev.jamieastley.kmpihole.api.mockHttpClient
import dev.jamieastley.kmpihole.api.models.auth.AuthRequest
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
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
        val sessionManager = SessionManager()
        val client = mockHttpClient { jsonResponse(loginSuccessJson) }
        val api = AuthApiImpl(client, config, sessionManager)

        api.login(AuthRequest(password = "secret"))

        assertEquals("test-sid-123", sessionManager.sid)
    }

    @Test
    fun `checkAuth returns session response`() = runTest {
        val sessionManager = SessionManager()
        val client = mockHttpClient { jsonResponse(loginSuccessJson) }
        val api = AuthApiImpl(client, config, sessionManager)

        val response = api.checkAuth()

        assertEquals(true, response.session.valid)
        assertEquals("test-sid-123", response.session.sid)
    }

    @Test
    fun `logout clears session manager`() = runTest {
        val sessionManager = SessionManager()
        sessionManager.store("test-sid-123")
        val client = mockHttpClient { jsonResponse("{}", HttpStatusCode.OK) }
        val api = AuthApiImpl(client, config, sessionManager)

        api.logout()

        assertNull(sessionManager.sid)
    }

    @Test
    fun `login throws Unauthorized on 401`() = runTest {
        val sessionManager = SessionManager()
        val client = mockHttpClient { jsonResponse(unauthorizedJson, HttpStatusCode.Unauthorized) }
        val api = AuthApiImpl(client, config, sessionManager)

        assertFailsWith<PiHoleException.Unauthorized> {
            api.login(AuthRequest(password = "wrong"))
        }
        assertNull(sessionManager.sid)
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
        val sessionManager = SessionManager()
        val client = mockHttpClient { jsonResponse(totpJson, HttpStatusCode.Unauthorized) }
        val api = AuthApiImpl(client, config, sessionManager)

        assertFailsWith<PiHoleException.Unauthorized> {
            api.checkAuth()
        }
    }
}
