package dev.jamieastley.kmpihole.api

import io.ktor.client.engine.curl.Curl

actual fun createPlatformHttpClientEngine() = Curl.create()