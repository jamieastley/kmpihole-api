package dev.jamieastley.kmpihole.api.queries

import dev.jamieastley.kmpihole.api.models.queries.QueriesResponse
import dev.jamieastley.kmpihole.api.models.queries.QueryFilters
import dev.jamieastley.kmpihole.api.models.queries.QuerySuggestionsResponse

interface QueriesApi {
    /** Get queries, optionally filtered by [filters]. */
    suspend fun getQueries(filters: QueryFilters = QueryFilters()): QueriesResponse

    /** Get available query filter suggestions (types, statuses, reply types, dnssec values). */
    suspend fun getQuerySuggestions(): QuerySuggestionsResponse
}
