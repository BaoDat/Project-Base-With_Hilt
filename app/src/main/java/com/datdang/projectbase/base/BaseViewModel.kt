package com.datdang.projectbase.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.datdang.projectbase.navigation.event.ActivityNavigationEvent
import com.datdang.projectbase.navigation.event.LoginNavigationEvent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    protected val _error = BehaviorSubject.create<Throwable>()
    protected val _activityNavigator = PublishSubject.create<ActivityNavigationEvent>()

    private val disposables by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    val error: Observable<Throwable>
        get() = _error

    val activityNavigator: Observable<ActivityNavigationEvent>
        get() = _activityNavigator

    val showLoading = MutableLiveData(false)

    protected fun <T> Single<T>.doShowLoading(): Single<T> = this
        .doOnSubscribe { showLoading.value = true }
        .doFinally { showLoading.value = false }

    protected fun Completable.doShowLoading(): Completable = this
        .doOnSubscribe { showLoading.value = true }
        .doFinally { showLoading.value = false }


    protected fun Disposable.addToDisposables() = addTo(disposables)
}