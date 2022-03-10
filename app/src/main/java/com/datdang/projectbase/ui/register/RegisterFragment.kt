package com.datdang.projectbase.ui.register

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.datdang.projectbase.R
import com.datdang.projectbase.base.BaseFragment
import com.datdang.projectbase.databinding.FragmentRegisterBinding
import com.datdang.projectbase.navigation.fragment.RegisterNavigator
import com.datdang.projectbase.ui.register.component.OnBirthdayClickListener
import com.datdang.projectbase.ui.register.vm.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val layoutId = R.layout.fragment_register
    override val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var navigator: RegisterNavigator

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        hideActionBar()

        binding.clickListener =  itemClickedListener

    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.navigator bindTo navigator::navigate

        viewModel.email bindTo {
            viewModel.emailError.value = null
        }

        viewModel.username bindTo {
            viewModel.usernameError.value = null
        }

        viewModel.birthDay bindTo {
            viewModel.birthDayError.value = null
        }

        viewModel.password bindTo {
            viewModel.passwordError.value = null
        }
        viewModel.confirmPassword bindTo {
            viewModel.confirmPasswordError.value = null
        }
    }

    private val itemClickedListener = object : OnBirthdayClickListener {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun onBirthdayClicked() {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            c.add(Calendar.YEAR, -18)

            val dpd = context?.let {
                DatePickerDialog(it, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.set(year, monthOfYear, dayOfMonth)
                    val birthday = SimpleDateFormat("MM/dd/yyyy").format(calendar.time)
                    binding.tvBirthday.text = birthday
                    viewModel.birthDay.value = birthday
                }, year, month, day
                )
            }

            dpd?.datePicker!!.maxDate = c.timeInMillis
            dpd.show()
        }

    }
}