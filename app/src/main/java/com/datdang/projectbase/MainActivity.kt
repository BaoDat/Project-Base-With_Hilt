package com.datdang.projectbase

import android.os.Bundle
import androidx.activity.viewModels
import com.datdang.projectbase.databinding.ActivityMainBinding
import com.datdang.projectbase.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun bindViewModel() {
    }
}