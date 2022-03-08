package com.datdang.projectbase.data.interceptor

import com.datdang.projectbase.data.session.LoginProfile
import com.datdang.projectbase.data.util.Constant
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(private val loginProfile: LoginProfile) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader(Constant.AUTH_TOKEN_HEADER, loginProfile.token)
            .build()
        return chain.proceed(request)
    }
}