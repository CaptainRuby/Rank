package com.ruby.rank.common.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    var instance: Retrofit

    init {
        //设定日志级别
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        //自定义OkHttpClient
        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        instance = Retrofit.Builder()
                .baseUrl(ServiceAPI.root)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

}