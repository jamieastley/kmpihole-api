package dev.jamieastley.kmpihole.api

import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClientEngine() = Darwin.create()