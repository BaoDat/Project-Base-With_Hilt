package com.datdang.projectbase.ui.register.vm

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseViewModel
import com.datdang.projectbase.base.NavigationEvent
import com.datdang.projectbase.domain.base.validation.ValidatableField
import com.datdang.projectbase.domain.base.validation.ValidationProblem
import com.datdang.projectbase.domain.exception.AppException
import com.datdang.projectbase.domain.model.RegistrationForm
import com.datdang.projectbase.domain.usecase.RegisterUseCase
import com.datdang.projectbase.extension.setDefaultScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val app: Application,
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel(app) {

    companion object {
        private const val PASSWORD_MIN_LENGTH = 6
    }

    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val birthDay = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    val emailError = MutableLiveData<Any?>()
    val usernameError = MutableLiveData<Any?>()
    val birthDayError = MutableLiveData<Any?>()
    val passwordError = MutableLiveData<Any?>()
    val confirmPasswordError = MutableLiveData<Any?>()

    private fun validateForm(): Boolean {
        return validateEmail(email.value) and validateUsername(username.value) and validateBirthday() and validatePassword(
            password.value
        ) and validateConfirmPassword(confirmPassword.value)
    }

    private fun validateEmail(email: String?): Boolean {
        email.orEmpty().also {
            return when {
                it.isBlank() -> {
                    emailError.value = R.string.register_error_email_required_message
                    false
                }
                !Patterns.EMAIL_ADDRESS.matcher(it).matches() -> {
                    emailError.value = R.string.register_error_email_invalid_message
                    false
                }
                else -> {
                    emailError.value = null
                    true
                }
            }
        }
    }

    private fun validateUsername(username: String?): Boolean {
        username.orEmpty().also {
            return when {
                it.isBlank() -> {
                    usernameError.value = R.string.register_error_username_required_message
                    false
                }
                else -> {
                    usernameError.value = null
                    true
                }
            }
        }
    }

    private fun validateBirthday(): Boolean {
        birthDay.value.also {
            return when (it) {
                null -> {
                    birthDayError.value = R.string.register_error_birthday_required_message
                    false
                }
                else -> {
                    birthDayError.value = null
                    true
                }
            }
        }
    }

    private fun validatePassword(password: String?): Boolean {
        password.orEmpty().also {
            return when {
                it.isBlank() -> {
                    passwordError.value = R.string.register_error_password_required_message
                    false
                }
                it.length in 1 until PASSWORD_MIN_LENGTH -> {
                    passwordError.value = R.string.register_error_password_too_short
                    false
                }
                else -> {
                    passwordError.value = null
                    true
                }
            }
        }
    }

    private fun validateConfirmPassword(confirmPassword: String?): Boolean {
        val isPasswordMatched = !TextUtils.equals(confirmPassword, password.value)
        confirmPassword.orEmpty().also {
            return when {
                it.isBlank() -> {
                    confirmPasswordError.value =
                        R.string.register_error_confirm_password_required_message
                    false
                }
                isPasswordMatched -> {
                    confirmPasswordError.value =
                        R.string.register_error_confirm_password_not_matched
                    false
                }
                else -> {
                    confirmPasswordError.value = null
                    true
                }
            }
        }
    }


    fun register() {
        if (validateForm()) {
            getForm()
                .flatMap {
                    registerUseCase.execute(it.getOrNull()!!)
                }
                .setDefaultScheduler()
                .doOnSubscribe{
                    showLoading.value = true
                }
                .subscribeBy(
                    onSuccess = {
                        showLoading.value = false
                        _navigator.onNext(NavigationEvent.ConfirmCode(email.value.orEmpty()))
                    },
                    onError = _error::onNext
                )
                .addToDisposables()
        }
    }

    private fun getForm(): Single<Result<RegistrationForm>> = Single.fromCallable {
        val errors = mutableListOf<Pair<ValidatableField, ValidationProblem>>()

        val email = email.value.orEmpty().also {
            when {
                it.isBlank() -> ValidationProblem.EMPTY
                !Patterns.EMAIL_ADDRESS.matcher(it).matches() -> ValidationProblem.INVALID_FORMAT
                else -> null
            }?.let {
                ValidatableField.EMAIL to it
            }?.let {
                errors.add(it)
            }
        }

        val username = username.value.orEmpty().also {
            when {
                it.isBlank() -> ValidationProblem.EMPTY
                else -> null
            }?.let {
                ValidatableField.USERNAME to it
            }?.let {
                errors.add(it)
            }
        }

        val pass = password.value.orEmpty().also {
            when {
                it.isBlank() -> ValidationProblem.EMPTY
                it.length < PASSWORD_MIN_LENGTH -> ValidationProblem.TOO_SHORT
                else -> null
            }?.let {
                ValidatableField.PASSWORD to it
            }?.let {
                errors.add(it)
            }
        }

        val date = birthDay.value.also {
            when (it) {
                null -> ValidationProblem.EMPTY
                else -> null
            }?.let {
                ValidatableField.BIRTHDAY to it
            }?.let {
                errors.add(it)
            }
        }

        if (errors.isEmpty()) {
            val form = RegistrationForm(
                username = username,
                password = pass,
                email = email,
                birthday = date!!
            )
            Result.success(form)
        } else {
            Result.failure(AppException.ValidationException(errors))
        }
    }

}
