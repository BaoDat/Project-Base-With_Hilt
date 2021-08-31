package com.datdang.projectbase.base.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.datdang.projectbase.extension.gone
import com.datdang.projectbase.extension.visible

@BindingAdapter("visible")
fun showHideView(view: View, show: Boolean) {
    if (show) view.visible() else view.gone()
}