package com.datdang.projectbase.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class RegisterResponseData {
    @field:Json(name = "message")
    var message: String = ""
}