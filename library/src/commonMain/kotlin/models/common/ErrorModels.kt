package dev.jamieastley.kmpihole.api.models.common

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(val error: ErrorDetail)

@Serializable
data class ErrorDetail(
    val key: String,
    val message: String,
    val hint: String? = null,
)

//@Serializable
//data class SuccessResponse(val status: String)
//
//@Serializable
//data class ProcessedItem(val item: String, val id: Int? = null)
//
//@Serializable
//data class ProcessedErrors(val item: String, val error: String)
//
//@Serializable
//data class ListsProcessed(
//    val success: List<ProcessedItem> = emptyList(),
//    val errors: List<ProcessedErrors> = emptyList(),
//)
