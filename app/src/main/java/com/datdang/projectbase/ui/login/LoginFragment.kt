package com.datdang.projectbase.ui.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseFragment
import com.datdang.projectbase.databinding.FragmentLoginBinding
import com.datdang.projectbase.extension.hideSoftKeyboard
import com.datdang.projectbase.navigation.fragment.LoginNavigator
import com.datdang.projectbase.ui.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val layoutId = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigator: LoginNavigator

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        hideActionBar()

    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.navigator bindTo navigator::navigate

        viewModel.hideKeyboard bindTo {
            this.activity?.hideSoftKeyboard()
        }
        viewModel.email bindTo {
            viewModel.isShowEmailAddressError.value = false
        }
        viewModel.password bindTo {
            viewModel.isShowPasswordError.value = false
        }
    }

}
