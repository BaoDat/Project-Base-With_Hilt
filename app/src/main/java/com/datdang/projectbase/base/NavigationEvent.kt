package com.datdang.projectbase.base

sealed class NavigationEvent {
    object Main : NavigationEvent()
    object CreateAccount : NavigationEvent()
    object ForgotPassword : NavigationEvent()
    data class ConfirmCode(val email: String) : NavigationEvent()
}
