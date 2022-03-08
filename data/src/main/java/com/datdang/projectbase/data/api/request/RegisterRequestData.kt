package com.datdang.projectbase.data.api.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequestData(
    @Json(name = "email")
    var email: String,
    @Json(name = "fullName")
    var fullName: String,
    @Json(name = "password")
    var password: String,
    @Json(name = "birthDate")
    var birthDate: String
)