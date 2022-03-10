package com.datdang.projectbase.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseActivity
import com.datdang.projectbase.databinding.ActivityLoginBinding
import com.datdang.projectbase.navigation.activity.ActivityNavigator
import com.datdang.projectbase.ui.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutId = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var activityNavigator: ActivityNavigator

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.activityNavigator bindTo activityNavigator::navigate
    }
}