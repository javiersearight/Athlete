package com.javy.athlete.data.source.remote.rest

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class OauthInterceptor @Inject constructor() : Interceptor {

    var tokenManager: TokenManager? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        tokenManager?.token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}