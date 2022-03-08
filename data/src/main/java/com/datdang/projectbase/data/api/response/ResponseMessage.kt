package com.datdang.projectbase.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseMessage<T>(
    @Json(name = "statusCode")
    var statusCode: Int,
    @Json(name = "status")
    var status: String,
    @Json(name = "message")
    var message: String,
    @Json(name = "responseData")
    var responseData: T? = null
)