package com.chyngyz.youtube.network

import com.chyngyz.youtube.data.room.InfoDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService{

//    private var apiInterface: ApiInterface? = null

    val networkModule : Module = module {
        factory { provideApi(get()) }
        single { getInstance()}
    }
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create()) //он для того чтобы мог работать с моделькой
            .build()
    }

    fun provideApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

    /*private fun buildRetrofit(): ApiInterface {
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
    }*/
}