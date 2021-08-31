package com.datdang.projectbase.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.datdang.projectbase.ui.login.vm.LoginViewModel
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseActivity
import com.datdang.projectbase.databinding.ActivityLoginBinding
import com.datdang.projectbase.ui.login.navigation.LoginNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutId = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigator: LoginNavigator

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun bindViewModel() {
        viewModel.navigator bindTo navigator::navigate
    }
}