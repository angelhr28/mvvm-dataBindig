package com.example.sadanime.helper

import com.example.sadanime.root.UnsafeOkHttpClient
import com.example.sadanime.helper.application.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//funciones que sera utiles en toda la app a futuro


fun getConexionRetrofit(url_base: String = Constants.ROOT_LOCAL): Retrofit {
    val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
    return Retrofit.Builder()
        .baseUrl(url_base)
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}