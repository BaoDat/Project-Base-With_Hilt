package com.datdang.projectbase.data.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestData(
    @Json(name = "email")
    var email: String,
    @Json(name = "password")
    var password: String
)