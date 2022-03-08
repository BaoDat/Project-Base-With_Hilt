package com.datdang.projectbase.data.repository

import com.datdang.projectbase.data.api.request.LoginRequestData
import com.datdang.projectbase.data.api.request.RegisterRequestData
import com.datdang.projectbase.data.api.response.ResponseData
import com.datdang.projectbase.data.api.response.ResponseMessage
import com.datdang.projectbase.data.extension.transform
import com.datdang.projectbase.data.service.AppServerService
import com.datdang.projectbase.data.session.LoginProfile
import com.datdang.projectbase.data.util.Constant
import com.datdang.projectbase.domain.model.LoginForm
import com.datdang.projectbase.domain.model.RegistrationForm
import com.datdang.projectbase.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appServerService: AppServerService,
    private val loginProfile: LoginProfile
) : UserRepository {

    override fun login(loginForm: LoginForm): Single<Unit> {
        return appServerService.login(LoginRequestData(loginForm.email, loginForm.password))
            .map {
                loginProfile.token = it.headers().get(Constant.AUTH_TOKEN_HEADER).orEmpty()
                it.body() as ResponseMessage<ResponseData>
            }
            .transform()
            .doOnSuccess {
                loginProfile.email = loginForm.email
            }
            .map { }
    }

    override fun register(registerForm: RegistrationForm): Single<Unit> {
        return appServerService.register(
            RegisterRequestData(
                registerForm.email,
                registerForm.username,
                registerForm.password,
                registerForm.birthday
            )
        )
            .transform()
            .map { }
    }
//
//    override fun confirmCode(confirmCodeForm: ConfirmCodeForm): Single<Unit> {
//        return appServerService.confirmCode(ConfirmCodeRequestData(confirmCodeForm.email, confirmCodeForm.activeCode))
//            .transform()
//            .map {}
//    }
//
//    override fun forgotPassword(forgotPasswordForm: ForgotPasswordForm): Single<Unit> {
//        return appServerService.forgotPassword(ForgotPasswordRequestData(forgotPasswordForm.email))
//            .transform()
//            .map { }
//    }
//
//    override fun confirmForgotPassword(confirmForgotPasswordForm: ConfirmForgotPasswordForm): Single<Unit> {
//        return appServerService.confirmForgotPassword(ConfirmForgotPasswordRequestData(confirmForgotPasswordForm.email, confirmForgotPasswordForm.activeCode))
//            .transform()
//            .map { }
//    }
//
//    override fun resetPassword(resetPasswordForm: ResetPasswordForm): Single<Unit> {
//        return appServerService.resetPassword(ResetPasswordRequestData(resetPasswordForm.email, resetPasswordForm.newPassword))
//            .transform()
//            .map { }
//    }
}
