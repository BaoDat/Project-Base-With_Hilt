package com.datdang.projectbase.ui.login.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseViewModel
import com.datdang.projectbase.base.NavigationEvent
import com.datdang.projectbase.utils.LiveEvent
import com.datdang.projectbase.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val app: Application
) : BaseViewModel(app){

    val isShowEmailAddressError: MutableLiveData<Boolean> = MutableLiveData(false)
    val isShowPasswordError: MutableLiveData<Boolean> = MutableLiveData(false)
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _hideKeyboard = LiveEvent<Unit>()
    val hideKeyboard: LiveData<Unit>
        get() = _hideKeyboard

    fun onLoginClick() {
        _hideKeyboard.trigger()
        if (validate()) {
            _navigator.onNext(NavigationEvent.Main)
        }
    }

    private fun validate(): Boolean {
        return validateEmail() and validatePassword()
    }

    private fun validatePassword(): Boolean {
        return when {
            password.value.isNullOrEmpty() -> {
                showPasswordError(app.getString(R.string.password_required_error))
                false
            }
            !Validator.validatePasswordLength(password.value) -> {
                showPasswordError(app.getString(R.string.password_length_error))
                false
            }
            else -> true
        }
    }

    private fun showPasswordError(error: String) {
        passwordError.value = error
        isShowPasswordError.value = true
    }

    private fun validateEmail(): Boolean {
        return when {
            email.value.isNullOrEmpty() -> {
                showEmailError(app.getString(R.string.email_required_error))
                false
            }
            !Validator.validateEmail(email.value) -> {
                showEmailError(app.getString(R.string.invalid_email_error))
                false
            }
            else -> true
        }
    }

    private fun showEmailError(error: String) {
        emailError.value = error
        isShowEmailAddressError.value = true
    }

    fun onCreateAccountClick() {
        _navigator.onNext(NavigationEvent.CreateAccount)
    }

    fun onForgotPasswordClick() {
        _navigator.onNext(NavigationEvent.ForgotPassword)
    }
}