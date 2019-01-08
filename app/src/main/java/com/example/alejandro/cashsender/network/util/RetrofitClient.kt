package com.example.alejandro.cashsender.network.util

import com.example.alejandro.cashsender.network.Constants
import com.example.alejandro.cashsender.network.service.MarvelService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    /**
     * Get Retrofit Instance
     */

    private fun getRetrofitInstance(interceptor: Interceptor?): Retrofit.Builder {
        try {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
                        .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor(interceptor!!)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
        } catch (ex: Exception) {
            throw ex
        }

    }

    /**
     * Get Marvel API.
     *
     * @return Marvel service
     */
    fun getMarvelService(url: String, interceptor: Interceptor?): MarvelService {
        return getRetrofitInstance(interceptor).baseUrl(url).build().create(MarvelService::class.java)
    }

}