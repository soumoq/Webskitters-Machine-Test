package com.webskitters.webskitters_machine_test.retrofit

import com.google.gson.GsonBuilder
import com.webskitters.webskitters_machine_test.util.ImpFun
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApiServiceBuilder {
    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder() //.addInterceptor(logging)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofit = Retrofit.Builder()
                .baseUrl(ImpFun.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }

    fun <T> buildService(serviceType: Class<T>?): T {
        return getRetrofit()!!.create(serviceType)
    }
}