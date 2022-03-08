package com.datdang.projectbase.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseData(
    @Json(name = "message")
    var message: String
)