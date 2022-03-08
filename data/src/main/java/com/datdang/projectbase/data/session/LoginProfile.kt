package com.datdang.projectbase.data.session

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginProfile @Inject constructor(@ApplicationContext context: Context) :
    AppSharedPreference(context, "profile") {

    private enum class ConfigKey {
        TOKEN, EMAIL
    }

    var token: String
        get() = super.getValue(ConfigKey.TOKEN.name).orEmpty()
        set(token) {
            super.set(ConfigKey.TOKEN.name, token)
        }

    var email: String
        get() = super.getValue(ConfigKey.EMAIL.name).orEmpty()
        set(email) {
            super.set(ConfigKey.EMAIL.name, email)
        }
}