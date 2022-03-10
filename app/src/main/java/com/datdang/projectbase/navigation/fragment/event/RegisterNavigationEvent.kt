package com.datdang.projectbase.navigation.fragment.event

sealed class RegisterNavigationEvent {
    data class ConfirmCode(val email: String) : RegisterNavigationEvent()
}
