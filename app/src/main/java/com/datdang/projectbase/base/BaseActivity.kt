package com.datdang.projectbase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.datdang.projectbase.BR
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

abstract class BaseActivity<TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    AppCompatActivity() {

    protected val compositeDisposable = CompositeDisposable()

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


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    protected fun setTitle(title: String) {
        supportActionBar?.run {
            this.title = title
            show()
        }
    }

    protected inline infix fun <T> LiveData<T>.bindTo(crossinline action: (T?) -> Unit) =
        this.observe(this@BaseActivity, { action(it) })

    protected inline infix fun <T> Observable<T>.bindTo(crossinline action: (T) -> Unit?) {
        observeOn(AndroidSchedulers.mainThread()).subscribe { action(it) }.addToDisposables()
    }

    protected fun Disposable.addToDisposables() = addTo(compositeDisposable)
}