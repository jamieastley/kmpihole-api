package dev.jamieastley.kmpihole.api

import io.ktor.client.engine.okhttp.*

actual fun createPlatformHttpClientEngine() = OkHttp.create()