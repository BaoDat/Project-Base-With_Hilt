package com.datdang.projectbase

import android.app.Application
import com.datdang.projectbase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val app: Application
) : BaseViewModel(app)