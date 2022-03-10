package com.datdang.projectbase.navigation.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import com.datdang.projectbase.base.BaseNavigator
import com.datdang.projectbase.base.BaseNavigatorImpl
import com.datdang.projectbase.navigation.fragment.event.RegisterNavigationEvent
import javax.inject.Inject

interface RegisterNavigator : BaseNavigator<RegisterNavigationEvent>

class RegisterNavigatorImpl @Inject constructor(fragment: Fragment) : BaseNavigatorImpl<RegisterNavigationEvent>(fragment),
    RegisterNavigator {

    override fun navigate(event: RegisterNavigationEvent) {
        when (event) {
            is RegisterNavigationEvent.ConfirmCode -> {
            }
        }
    }
}