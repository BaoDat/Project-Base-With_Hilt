package com.datdang.projectbase.domain.model


data class RegistrationForm(
    val email: String,
    val username: String,
    val birthday: String,
    val password: String,
)