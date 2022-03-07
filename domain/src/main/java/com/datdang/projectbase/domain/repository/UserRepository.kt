package com.datdang.projectbase.domain.repository

import com.datdang.projectbase.domain.model.LoginForm
import com.datdang.projectbase.domain.model.RegistrationForm
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun login(loginForm: LoginForm): Single<Unit>
    fun register(registerForm: RegistrationForm): Single<Unit>
//    fun confirmCode(confirmCodeForm: ConfirmCodeForm): Single<Unit>
//    fun forgotPassword(forgotPasswordForm: ForgotPasswordForm): Single<Unit>
//    fun confirmForgotPassword(confirmForgotPasswordForm: ConfirmForgotPasswordForm): Single<Unit>
//    fun resetPassword(resetPasswordForm: ResetPasswordForm): Single<Unit>
}
