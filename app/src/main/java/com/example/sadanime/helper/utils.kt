package com.example.sadanime.helper

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.DataBindingUtil
import com.example.sadanime.root.UnsafeOkHttpClient
import com.example.sadanime.helper.application.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//funciones que sera utiles en toda la app a futuro

fun getConexionRetrofit(url_base: String = Constants.ROOT): Retrofit {
    val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
    return Retrofit.Builder()
        .baseUrl(url_base)
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun isNullOrEmpty(text: Any): Boolean {
    return when (text) {
        is String -> text.trim().isEmpty()
        is EditText -> text.text.trim().isEmpty()
        else -> false
    }
}

fun setCircularImage(context: Context, id: Int): RoundedBitmapDrawable {
    val res = context.resources
    val src = BitmapFactory.decodeResource(res, id)
    val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(res, src)
    roundedBitmapDrawable.cornerRadius = Math.max(src.width, src.height) / 2.0f
    return roundedBitmapDrawable
}