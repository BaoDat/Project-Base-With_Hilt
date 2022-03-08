package com.datdang.projectbase.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseNavigator
import com.datdang.projectbase.base.BaseNavigatorImpl
import com.datdang.projectbase.base.NavigationEvent
import javax.inject.Inject

interface LoginNavigator : BaseNavigator

class LoginNavigatorImpl @Inject constructor(fragment: Fragment) : BaseNavigatorImpl(fragment),
    LoginNavigator {

    override fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Main -> {
//                fragment.startActivity(
//                    Intent(
//                        fragment.context,
//                        MainActivity::class.java
//                    )
//                )
//                fragment.activity?.finish()
            }
            is NavigationEvent.CreateAccount -> {
                findNavController()?.navigate(R.id.action_login_to_register)
            }
            is NavigationEvent.ForgotPassword -> {
//                findNavController()?.navigate(R.id.action_login_to_forgot_password)
            }
            is NavigationEvent.ConfirmCode -> {

            }
        }
    }
}