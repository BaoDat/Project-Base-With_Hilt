package com.datdang.projectbase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.datdang.projectbase.R
import com.datdang.projectbase.domain.exception.AppException
import com.datdang.projectbase.utils.Dialog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import com.datdang.projectbase.BR

abstract class BaseFragment<TBinding : ViewDataBinding, TViewModel : BaseViewModel> : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    protected lateinit var binding: TBinding

    protected abstract val layoutId: Int
    protected abstract val viewModel: TViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        initView(savedInstanceState)
        bindViewModel()
        return binding.root
    }

    protected open fun initView(savedInstanceState: Bundle?) {}

    protected open fun bindViewModel() {
        viewModel.error bindTo ::handleError
    }

    protected open fun handleError(exception: Throwable) {
        Timber.e(exception)
        viewModel.showLoading.value = false
        when (exception) {
            is AppException.ApiException -> showErrorDialog(exception.uiMessage)
            else -> showErrorDialog(getString(R.string.generic_error_message))
        }
    }

    protected fun showErrorDialog(message: String) {
        context?.run {
            Dialog.createOneButtonDialog(
                this,
                getString(R.string.error),
                message,
                getString(R.string.ok)
            ) { dialog, _ -> dialog?.dismiss() }.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    protected fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    protected fun setTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.run {
            this.title = title
            show()
        }
    }

    protected inline infix fun <T> LiveData<T>.bindTo(crossinline action: (T?) -> Unit) =
        this.observe(this@BaseFragment, { action(it) })

    protected inline infix fun <T> Observable<T>.bindTo(crossinline action: (T) -> Unit?) {
        observeOn(AndroidSchedulers.mainThread()).subscribe { action(it) }.addToDisposables()
    }

    protected fun Disposable.addToDisposables() = addTo(compositeDisposable)
}