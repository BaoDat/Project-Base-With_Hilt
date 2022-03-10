package com.datdang.projectbase.navigation.fragment.event

sealed class LoginNavigationEvent {
    object Main : LoginNavigationEvent()
    object CreateAccount : LoginNavigationEvent()
    object ForgotPassword : LoginNavigationEvent()
}
