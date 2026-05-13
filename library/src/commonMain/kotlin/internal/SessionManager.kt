package dev.jamieastley.kmpihole.api.internal

internal class SessionManager {
    @kotlin.concurrent.Volatile
    var sid: String? = null
        private set

    fun store(sid: String) {
        this.sid = sid
    }

    fun clear() {
        this.sid = null
    }
}
