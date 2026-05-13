package dev.jamieastley.kmpihole.api.internal

sealed class PiHoleException(message: String) : Exception(message) {
    class Unauthorized(hint: String? = null) :
        PiHoleException("401 Unauthorized${hint?.let { ": $it" } ?: ""}")

    class BadRequest(key: String, hint: String? = null) :
        PiHoleException("400 Bad Request [$key]${hint?.let { ": $it" } ?: ""}")

    class NotFound(hint: String? = null) :
        PiHoleException("404 Not Found${hint?.let { ": $it" } ?: ""}")

    class TooManyRequests(hint: String? = null) :
        PiHoleException("429 Too Many Requests${hint?.let { ": $it" } ?: ""}")

    class ServerError(code: Int, hint: String? = null) :
        PiHoleException("$code Server Error${hint?.let { ": $it" } ?: ""}")

    class Unknown(code: Int, body: String) :
        PiHoleException("HTTP $code: $body")
}
