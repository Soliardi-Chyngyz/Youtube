package com.chyngyz.youtube.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService(){

    companion object {
        private var apiInterface: ApiInterface? = null

        fun getInstance(): ApiInterface? {
            if(apiInterface == null)
                apiInterface = buildRetrofit()
            return apiInterface
        }

        private fun buildRetrofit(): ApiInterface {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create()) //он для того чтобы мог работать с моделькой
                .client(okHttpClient)
                .build()
                .create(ApiInterface::class.java)
        }
    }
}