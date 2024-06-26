package com.sciflare.task.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * retrofit is type safe http client  fro android and developed by java square
 * making network request to retrofit restful webservice using anotations
 * retrofit provide list of anatations tpo each http method those are GET PUT DELETE PATCH POST
 */
object RetrofitApi {

    private var retrofit: Retrofit? = null
    private fun okHttpClientSever(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()

    private var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private var mOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()
    private var gson: Gson? = GsonBuilder()
        .setLenient()
        .create()
    fun retrofitInstance(): Retrofit =
        retrofit
            ?: Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson!!))
                .baseUrl(Constant.API_URL)
                .client(okHttpClientSever())
                .client(mOkHttpClient)
                .build()
}

    object Constant {
        const val API_URL = "https://crudcrud.com/api/2b2a8f21391a4a019efdb74d983c871e/"
    }
