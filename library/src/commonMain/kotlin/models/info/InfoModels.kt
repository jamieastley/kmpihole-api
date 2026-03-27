package dev.jamieastley.kmpihole.api.models.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ─── Client Info ──────────────────────────────────────────────────────────────

@Serializable
data class ClientInfoResponse(
    @SerialName("remote_addr") val remoteAddr: String? = null,
    @SerialName("http_version") val httpVersion: String? = null,
    val method: String? = null,
    val headers: List<HttpHeader> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class HttpHeader(val name: String, val value: String)

// ─── System Info ──────────────────────────────────────────────────────────────

@Serializable
data class SystemInfoResponse(
    val system: SystemInfo,
    val took: Double = 0.0,
)

@Serializable
data class SystemInfo(
    val uptime: Long,
    val memory: MemoryInfo? = null,
    val procs: Int? = null,
    val cpu: CpuInfo? = null,
    val ftl: FtlProcess? = null,
)

@Serializable
data class MemoryInfo(
    val ram: MemoryStats? = null,
    val swap: MemoryStats? = null,
)

@Serializable
data class MemoryStats(
    val total: Long,
    val free: Long,
    val used: Long,
    val available: Long? = null,
    @SerialName("%used") val percentUsed: Double,
)

@Serializable
data class CpuInfo(
    val nprocs: Int,
    @SerialName("%cpu") val percentCpu: Double? = null,
)

@Serializable
data class FtlProcess(
    val pid: Int? = null,
    @SerialName("%mem") val percentMem: Double? = null,
    @SerialName("%cpu") val percentCpu: Double? = null,
)

// ─── Host Info ────────────────────────────────────────────────────────────────

@Serializable
data class HostInfoResponse(
    val host: HostInfo,
    val took: Double = 0.0,
)

@Serializable
data class HostInfo(
    val uname: UnameInfo? = null,
    val model: String? = null,
    val dmi: DmiInfo? = null,
)

@Serializable
data class UnameInfo(
    val sysname: String? = null,
    val nodename: String? = null,
    val release: String? = null,
    val version: String? = null,
    val machine: String? = null,
)

@Serializable
data class DmiInfo(
    val manufacturer: String? = null,
    val model: String? = null,
)

// ─── FTL Info ─────────────────────────────────────────────────────────────────

@Serializable
data class FtlInfoResponse(
    val ftl: FtlInfo,
    val took: Double = 0.0,
)

@Serializable
data class FtlInfo(
    val database: FtlDatabase? = null,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("query_frequency") val queryFrequency: Double? = null,
    val pid: Int? = null,
    val uptime: Double? = null,
    @SerialName("%mem") val percentMem: Double? = null,
    @SerialName("%cpu") val percentCpu: Double? = null,
    @SerialName("allow_destructive") val allowDestructive: Boolean? = null,
)

@Serializable
data class FtlDatabase(
    val gravity: DatabaseInfo? = null,
)

@Serializable
data class DatabaseInfo(
    val size: Long? = null,
    val filepath: String? = null,
)

// ─── Sensors ──────────────────────────────────────────────────────────────────

@Serializable
data class SensorsResponse(
    val sensors: SensorsInfo,
    val took: Double = 0.0,
)

@Serializable
data class SensorsInfo(
    val list: List<SensorEntry> = emptyList(),
    @SerialName("cpu_temp") val cpuTemp: Double? = null,
    @SerialName("hot_limit") val hotLimit: Double? = null,
    val unit: String? = null,
)

@Serializable
data class SensorEntry(
    val name: String,
    val value: Double,
    val unit: String? = null,
)

// ─── Database Info ────────────────────────────────────────────────────────────

@Serializable
data class DbInfoResponse(
    val database: DbInfo? = null,
    val took: Double = 0.0,
)

@Serializable
data class DbInfo(
    val size: Long? = null,
    val type: String? = null,
    val mode: String? = null,
    val queries: Long? = null,
    @SerialName("earliest_timestamp") val earliestTimestamp: Double? = null,
    @SerialName("sqlite_version") val sqliteVersion: String? = null,
)

// ─── Version ──────────────────────────────────────────────────────────────────

@Serializable
data class VersionResponse(
    val version: VersionInfo,
    val took: Double = 0.0,
)

@Serializable
data class VersionInfo(
    val core: ComponentVersion? = null,
    val web: ComponentVersion? = null,
    val ftl: ComponentVersion? = null,
    val docker: ComponentVersion? = null,
)

@Serializable
data class ComponentVersion(
    val local: LocalVersion? = null,
    val remote: RemoteVersion? = null,
)

@Serializable
data class LocalVersion(
    val branch: String? = null,
    val tag: String? = null,
    val hash: String? = null,
    val version: String? = null,
    val date: String? = null,
)

@Serializable
data class RemoteVersion(
    val tag: String? = null,
    val version: String? = null,
)

// ─── Messages ─────────────────────────────────────────────────────────────────

@Serializable
data class MessagesResponse(
    val messages: List<DiagnosticMessage> = emptyList(),
    val took: Double = 0.0,
)

@Serializable
data class DiagnosticMessage(
    val id: Int,
    val type: String,
    val message: String,
    val blob: String? = null,
    @SerialName("timestamp") val timestamp: Long,
)

@Serializable
data class MessageCountResponse(
    val count: Int,
    val took: Double = 0.0,
)

// ─── Metrics Info ─────────────────────────────────────────────────────────────

@Serializable
data class MetricsInfoResponse(
    val metrics: MetricsInfo? = null,
    val took: Double = 0.0,
)

@Serializable
data class MetricsInfo(
    val dns: DnsMetrics? = null,
    val dhcp: DhcpMetrics? = null,
)

@Serializable
data class DnsMetrics(
    val queries: Int? = null,
    val blocked: Int? = null,
    val cached: Int? = null,
    val forwarded: Int? = null,
)

@Serializable
data class DhcpMetrics(
    val leases: Int? = null,
)

// ─── Login Info ───────────────────────────────────────────────────────────────

@Serializable
data class LoginInfoResponse(
    @SerialName("https_port") val httpsPort: Int? = null,
    val dns: Boolean? = null,
    val took: Double = 0.0,
)
