package com.datdang.projectbase.base.databinding

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.datdang.projectbase.R
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("errorText")
fun bindErrorText(view: TextView, strOrResId: Any?) {
    if (strOrResId == null) {
        return
    }
    ContextCompat.getColor(view.context, R.color.red).let(
        view::setTextColor
    )
    if (strOrResId is Int) {
        view.text = view.context.getString(strOrResId)
    } else {
        view.text = strOrResId as String
    }
}

@BindingAdapter("passwordToggleEnabled", requireAll = false)
fun bindPasswordToggleEnabled(view: TextInputLayout, isVisible: Boolean?) {
    view.isPasswordVisibilityToggleEnabled = isVisible ?: false
}