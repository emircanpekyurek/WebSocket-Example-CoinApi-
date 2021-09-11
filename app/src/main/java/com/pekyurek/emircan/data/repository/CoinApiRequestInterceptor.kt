package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.domain.constants.CoinApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class CoinApiRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("X-CoinAPI-Key", CoinApiConstants.KEY)
            .build()

        return chain.proceed(request)
    }

}
