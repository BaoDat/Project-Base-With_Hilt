package com.datdang.projectbase.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.datdang.projectbase.BR

abstract class BaseActivity<TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    AppCompatActivity() {

    protected lateinit var binding: TBinding

    protected abstract val layoutId: Int
    protected abstract val viewModel: TViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        bindViewModel()
        initView(savedInstanceState)
    }

    open fun initView(savedInstanceState: Bundle?) {}

    open fun bindViewModel() {}

    open fun handleError(exception: Throwable) {
        //TODO handle error
    }
}