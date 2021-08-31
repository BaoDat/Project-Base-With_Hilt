package com.datdang.projectbase.ui.login.navigation


sealed class LoginNavigationEvent {
    object NavigateMain : LoginNavigationEvent()
}
