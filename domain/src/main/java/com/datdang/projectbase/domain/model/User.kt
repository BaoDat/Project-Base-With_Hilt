package com.datdang.projectbase.domain.model

data class User(
    var id: String,
    var fullName: String,
    var email: String,
    var birthDate: String,
    var isActive: Boolean,
    var isFriend: Boolean = false
)