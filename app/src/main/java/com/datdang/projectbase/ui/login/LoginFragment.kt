package com.datdang.projectbase.ui.login

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseFragment
import com.datdang.projectbase.databinding.FragmentLoginBinding
import com.datdang.projectbase.extension.hideSoftKeyboard
import com.datdang.projectbase.ui.login.navigation.LoginNavigator
import com.datdang.projectbase.ui.login.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.android.material.composethemeadapter.MdcTheme
import javax.inject.Inject
import androidx.navigation.compose.rememberNavController
import com.google.android.material.internal.NavigationMenuItemView

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val layoutId = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigator: LoginNavigator

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        hideActionBar()
        binding.greeting.setContent {
            MdcTheme {
                TestBox()
            }
        }
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

@Preview
@Composable
private fun MainScreen(){
    val navController =  rememberNavController()

}


@Preview
@Composable
private fun Greeting() {
    Text(
        text = stringResource(R.string.greeting),
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Preview
@Composable
private fun TestBox(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            text = "Jetpack Compose",
            color = Color.Blue,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )

    }
}