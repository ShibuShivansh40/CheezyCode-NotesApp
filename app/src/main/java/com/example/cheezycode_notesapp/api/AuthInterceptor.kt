package com.example.cheezycode_notesapp.api

import com.example.cheezycode_notesapp.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        // Here we will be adding a header to the request we are sending
        val request = chain.request().newBuilder()
        val token = tokenManager.getToken()
        request.addHeader("authorization" , "Bearer $token")

        return chain.proceed(request.build())
    }


}