package com.datdang.projectbase.navigation.event

sealed class LoginNavigationEvent {
    object Main : LoginNavigationEvent()
    object CreateAccount : LoginNavigationEvent()
    object ForgotPassword : LoginNavigationEvent()
    data class ConfirmCode(val email: String) : LoginNavigationEvent()
}
