package dev.jamieastley.kmpihole.api.internal

import dev.jamieastley.kmpihole.api.models.common.ApiError
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal suspend inline fun <reified T> HttpResponse.bodyOrThrow(): T {
    if (status.isSuccess()) return body()
    throwForStatus()
}

internal suspend fun HttpResponse.throwForStatus(): Nothing {
    val error = runCatching { body<ApiError>() }.getOrNull()
    val key = error?.error?.key ?: ""
    val hint = error?.error?.hint

    throw when (status) {
        HttpStatusCode.Unauthorized -> PiHoleException.Unauthorized(hint)
        HttpStatusCode.BadRequest -> PiHoleException.BadRequest(key, hint)
        HttpStatusCode.NotFound -> PiHoleException.NotFound(hint)
        HttpStatusCode.TooManyRequests -> PiHoleException.TooManyRequests(hint)
        else -> if (status.value in 500..599) {
            PiHoleException.ServerError(status.value, hint)
        } else {
            PiHoleException.Unknown(status.value, bodyAsText())
        }
    }
}
