package com.datdang.projectbase.data.extension

import com.datdang.projectbase.data.api.response.ResponseMessage
import com.datdang.projectbase.domain.exception.AppException
import com.datdang.projectbase.domain.exception.ConnectivityErrorSource
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import timber.log.Timber
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.UnknownHostException

fun <T> Single<ResponseMessage<T>>.transform() = flatMap {
    if (it.statusCode == 200 && it.responseData != null) {
        Single.just(it.responseData)
    } else {
        Single.error(
            AppException.ApiException(
                errorCode = it.statusCode,
                uiMessage = it.message
            )
        )
    }
}.onErrorResumeNext {
    Timber.e(it)
    when (it) {
        is UnknownHostException,
        is InterruptedIOException -> Single.error(
            AppException.NoConnectivityError(
                ConnectivityErrorSource.MOBILE_DEVICE
            )
        )
        is HttpException,
        is SocketException -> Single.error(AppException.NoConnectivityError(ConnectivityErrorSource.SERVER))
        is AppException.ApiException -> Single.error(it)
        else -> Single.error(AppException.UnknownException(it as Exception))
    }
}