package dev.jamieastley.kmpihole.api

import dev.jamieastley.kmpihole.api.action.ActionApi
import dev.jamieastley.kmpihole.api.action.ActionApiImpl
import dev.jamieastley.kmpihole.api.auth.AuthApi
import dev.jamieastley.kmpihole.api.auth.AuthApiImpl
import dev.jamieastley.kmpihole.api.clients.ClientsApi
import dev.jamieastley.kmpihole.api.clients.ClientsApiImpl
import dev.jamieastley.kmpihole.api.config.ConfigApi
import dev.jamieastley.kmpihole.api.config.ConfigApiImpl
import dev.jamieastley.kmpihole.api.dhcp.DhcpApi
import dev.jamieastley.kmpihole.api.dhcp.DhcpApiImpl
import dev.jamieastley.kmpihole.api.dns.DnsApi
import dev.jamieastley.kmpihole.api.dns.DnsApiImpl
import dev.jamieastley.kmpihole.api.domains.DomainsApi
import dev.jamieastley.kmpihole.api.domains.DomainsApiImpl
import dev.jamieastley.kmpihole.api.groups.GroupsApi
import dev.jamieastley.kmpihole.api.groups.GroupsApiImpl
import dev.jamieastley.kmpihole.api.history.HistoryApi
import dev.jamieastley.kmpihole.api.history.HistoryApiImpl
import dev.jamieastley.kmpihole.api.info.InfoApi
import dev.jamieastley.kmpihole.api.info.InfoApiImpl
import dev.jamieastley.kmpihole.api.internal.SessionManager
import dev.jamieastley.kmpihole.api.lists.ListsApi
import dev.jamieastley.kmpihole.api.lists.ListsApiImpl
import dev.jamieastley.kmpihole.api.logs.LogsApi
import dev.jamieastley.kmpihole.api.logs.LogsApiImpl
import dev.jamieastley.kmpihole.api.network.NetworkApi
import dev.jamieastley.kmpihole.api.network.NetworkApiImpl
import dev.jamieastley.kmpihole.api.padd.PaddApi
import dev.jamieastley.kmpihole.api.padd.PaddApiImpl
import dev.jamieastley.kmpihole.api.queries.QueriesApi
import dev.jamieastley.kmpihole.api.queries.QueriesApiImpl
import dev.jamieastley.kmpihole.api.search.SearchApi
import dev.jamieastley.kmpihole.api.search.SearchApiImpl
import dev.jamieastley.kmpihole.api.stats.StatsApi
import dev.jamieastley.kmpihole.api.stats.StatsApiImpl
import dev.jamieastley.kmpihole.api.teleporter.TeleporterApi
import dev.jamieastley.kmpihole.api.teleporter.TeleporterApiImpl
import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun createPlatformHttpClientEngine(): HttpClientEngine

/**
 * Main entry point for the KMPiHole API client.
 *
 * Usage:
 * ```kotlin
 * val client = KmpiholeClient(KmpiholeClientConfig(host = "192.168.1.1", useTls = false))
 * client.auth.login(AuthRequest(password = "secret"))
 * val summary = client.stats.getSummary()
 * ```
 */
class KmpiholeClient(
    clientConfig: KmpiholeClientConfig,
    engine: HttpClientEngine = createPlatformHttpClientEngine(),
) {
    private val sessionManager = SessionManager()

    private val httpClient: HttpClient = HttpClient(engine) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
            })
        }
        install(HttpTimeout) {
            connectTimeoutMillis = clientConfig.connectTimeoutMs
            requestTimeoutMillis = clientConfig.requestTimeoutMs
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            sessionManager.sid?.let { headers.append("sid", it) }
        }
    }

    val auth: AuthApi = AuthApiImpl(httpClient, clientConfig, sessionManager)
    val stats: StatsApi = StatsApiImpl(httpClient, clientConfig)
    val history: HistoryApi = HistoryApiImpl(httpClient, clientConfig)
    val queries: QueriesApi = QueriesApiImpl(httpClient, clientConfig)
    val dns: DnsApi = DnsApiImpl(httpClient, clientConfig)
    val domains: DomainsApi = DomainsApiImpl(httpClient, clientConfig)
    val groups: GroupsApi = GroupsApiImpl(httpClient, clientConfig)
    val clients: ClientsApi = ClientsApiImpl(httpClient, clientConfig)
    val lists: ListsApi = ListsApiImpl(httpClient, clientConfig)
    val info: InfoApi = InfoApiImpl(httpClient, clientConfig)
    val config: ConfigApi = ConfigApiImpl(httpClient, clientConfig)
    val network: NetworkApi = NetworkApiImpl(httpClient, clientConfig)
    val action: ActionApi = ActionApiImpl(httpClient, clientConfig)
    val dhcp: DhcpApi = DhcpApiImpl(httpClient, clientConfig)
    val logs: LogsApi = LogsApiImpl(httpClient, clientConfig)
    val search: SearchApi = SearchApiImpl(httpClient, clientConfig)
    val teleporter: TeleporterApi = TeleporterApiImpl(httpClient, clientConfig)
    val padd: PaddApi = PaddApiImpl(httpClient, clientConfig)
}