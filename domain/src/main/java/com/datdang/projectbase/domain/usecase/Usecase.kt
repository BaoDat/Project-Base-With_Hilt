package com.datdang.projectbase.domain.usecase

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface UseCase {

    interface Action<in TParam> : Single<TParam, Unit>

    interface ActionSuccessful<in TParam> {
        fun execute(param: TParam): Completable
    }

    interface Single<in TParam, TResult> {
        fun execute(param: TParam): io.reactivex.rxjava3.core.Single<TResult>
    }

    interface SingleSuccessful<in TParam, TResult> {
        fun execute(param: TParam): io.reactivex.rxjava3.core.Single<TResult>
    }

    interface Continuous<in TParam, TResult> {
        fun execute(param: TParam): Observable<TResult>
    }
}
