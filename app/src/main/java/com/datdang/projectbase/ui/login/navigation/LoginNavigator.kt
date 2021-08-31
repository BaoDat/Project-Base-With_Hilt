package com.datdang.projectbase.ui.login.navigation

import android.widget.Toast
import com.datdang.projectbase.ui.login.LoginActivity
import javax.inject.Inject

interface LoginNavigator {
    fun navigate(event: LoginNavigationEvent)
}

class CreateBarCodeNavigatorImpl @Inject constructor(
    private val activity: LoginActivity
) : LoginNavigator {

    override fun navigate(event: LoginNavigationEvent) {
        when (event) {
            is LoginNavigationEvent.NavigateMain -> {
                activity.run {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}