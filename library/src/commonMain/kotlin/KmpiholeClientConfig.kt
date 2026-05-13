package dev.jamieastley.kmpihole.api

data class KmpiholeClientConfig(
    val host: String,
    val port: Int = 443,
    val path: String = "api",
    val useTls: Boolean = true,
    val connectTimeoutMs: Long = 10_000L,
    val requestTimeoutMs: Long = 30_000L,
) {
    val baseUrl: String
        get() {
            val scheme = if (useTls) "https" else "http"
            // Remove leading slash from path if present
            val path = if (path.startsWith("/")) path.substring(1) else path
            return "$scheme://$host:$port/$path"
        }
}
