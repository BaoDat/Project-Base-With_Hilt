package com.datdang.projectbase.domain.exception

import com.datdang.projectbase.domain.base.validation.ValidatableField
import com.datdang.projectbase.domain.base.validation.ValidationProblem


sealed class AppException : Throwable() {

    data class ApiException(
        var uiMessage: String = "",
        var debugMessage: String = "",
        var errorCode: Int = -1,
        var exception: Exception? = null,
    ) : AppException()

    data class UnknownException(val e: Exception) : AppException()

    data class NoConnectivityError(val source: ConnectivityErrorSource) : AppException()

    class ValidationException(val problems: List<Pair<ValidatableField, ValidationProblem>>) : AppException()

}

enum class ConnectivityErrorSource {
    MOBILE_DEVICE,
    SERVER
}
