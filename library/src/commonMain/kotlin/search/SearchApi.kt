package dev.jamieastley.kmpihole.api.search

import dev.jamieastley.kmpihole.api.models.search.SearchResponse

interface SearchApi {
    suspend fun search(domain: String): SearchResponse
}
