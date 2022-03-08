package com.datdang.projectbase.data.service

import com.datdang.projectbase.data.api.ApiMethod
import com.datdang.projectbase.data.api.request.*
import com.datdang.projectbase.data.api.response.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppServerService {

    @POST(ApiMethod.REGISTER)
    fun register(@Body body: RegisterRequestData): Single<ResponseMessage<RegisterResponseData>>

//    @POST(ApiMethod.CONFIRM_CODE)
//    fun confirmCode(@Body body: ConfirmCodeRequestData): Single<ResponseMessage<ConfirmCodeResponseData>>
//
//    @POST(ApiMethod.FORGOT_PASSWORD)
//    fun forgotPassword(@Body body: ForgotPasswordRequestData): Single<ResponseMessage<ForgotPasswordResponseData>>
//
//    @POST(ApiMethod.CONFIRM_FORGOT_PASSWORD)
//    fun confirmForgotPassword(@Body body: ConfirmForgotPasswordRequestData): Single<ResponseMessage<ConfirmForgotPasswordResponseData>>
//
//    @POST(ApiMethod.RESET_PASSWORD)
//    fun resetPassword(@Body body: ResetPasswordRequestData): Single<ResponseMessage<ResetResponseData>>

    @POST(ApiMethod.LOGIN)
    fun login(@Body body: LoginRequestData): Single<Response<ResponseMessage<ResponseData>>>

}
