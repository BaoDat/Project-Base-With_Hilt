package com.datdang.projectbase.data.interceptor

import android.content.Context
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.inject.Inject

class MockInterceptor @Inject constructor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri()
        Timber.d("Fetching uri: %s", uri.toString())
        val requestPath = uri.path.toLowerCase(Locale.ENGLISH)
        val fileName = String.format(
            "json/response_%s.json",
            requestPath.replace(Regex("[/.]"), "")
        )
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            Timber.e("Thread.sleep() => %s", e.message)
        }
        var jsonFile: InputStream? = null
        val buffer: ByteArray
        try {
            jsonFile = context.assets.open(fileName)
            buffer = ByteArray(jsonFile.available())
            jsonFile.read(buffer)
        } finally {
            jsonFile?.close()
        }

        val responseString = String(buffer)
        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                ResponseBody.create(
                    "application/json; charset=UTF-8".toMediaTypeOrNull(),
                    responseString.toByteArray(charset("UTF-8"))
                )
            )
            .addHeader("content-type", "application/json; charset=UTF-8")
            .build()
    }
}