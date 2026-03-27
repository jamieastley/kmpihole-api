package dev.jamieastley.kmpihole.api.teleporter

import dev.jamieastley.kmpihole.api.models.teleporter.TeleporterImportResponse

interface TeleporterApi {
    /** Export Pi-hole settings as a zip archive. Returns raw bytes. */
    suspend fun exportSettings(): ByteArray

    /** Import Pi-hole settings from a zip archive [data]. */
    suspend fun importSettings(data: ByteArray, filename: String = "pihole-backup.zip"): TeleporterImportResponse
}
