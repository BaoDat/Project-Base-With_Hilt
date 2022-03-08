package com.datdang.projectbase.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "_id")
    var id: String,
    @Json(name = "fullName")
    var fullName: String,
    @Json(name = "email")
    var email: String,
    @Json(name = "birthDate")
    var birthDate: String,
    @Json(name = "active")
    var isActive: Boolean
)