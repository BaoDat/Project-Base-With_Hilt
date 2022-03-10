package com.datdang.projectbase.navigation.fragment

import androidx.fragment.app.Fragment
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseNavigator
import com.datdang.projectbase.base.BaseNavigatorImpl
import com.datdang.projectbase.navigation.fragment.event.LoginNavigationEvent
import javax.inject.Inject

interface LoginNavigator : BaseNavigator<LoginNavigationEvent>

class LoginNavigatorImpl @Inject constructor(fragment: Fragment) : BaseNavigatorImpl<LoginNavigationEvent>(fragment),
    LoginNavigator {

    override fun navigate(event: LoginNavigationEvent) {
        when (event) {
            is LoginNavigationEvent.Main -> {
//                fragment.startActivity(
//                    Intent(
//                        fragment.context,
//                        MainActivity::class.java
//                    )
//                )
//                fragment.activity?.finish()
            }
            is LoginNavigationEvent.CreateAccount -> {
                findNavController()?.navigate(R.id.action_login_to_register)
            }
            is LoginNavigationEvent.ForgotPassword -> {
//                findNavController()?.navigate(R.id.action_login_to_forgot_password)
            }
        }
    }
}