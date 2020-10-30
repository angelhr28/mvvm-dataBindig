package com.example.sadanime.helper

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.DataBindingUtil
import com.example.sadanime.R
import com.example.sadanime.root.UnsafeOkHttpClient
import com.example.sadanime.helper.application.Constants
import com.example.sadanime.root.ctx
import com.google.android.material.snackbar.Snackbar
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
    roundedBitmapDrawable.cornerRadius = src.width.coerceAtLeast(src.height) / 2.0f
    return roundedBitmapDrawable
}

fun calcularPxToDps(context: Context, pixels: Int): Int {
    val scale = context.resources.displayMetrics.density
    return (pixels*scale + 0.5f).toInt()
}

fun View.showSimpleSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG, margin: Int = 20, function: (Snackbar) -> Unit){
    val snackbar = Snackbar.make(this, message, duration)
    val view = snackbar.view
    val textview = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textview.setTextColor(ContextCompat.getColor(ctx, R.color.colorWhite))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        view.elevation = 120.0f
    }
    val fondo = ContextCompat.getDrawable(ctx, R.drawable.snackbar_body)
    view.background = fondo
    val margen :ViewGroup.MarginLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    margen.setMargins(20, 0, 20, calcularPxToDps(ctx, margin))
    function(snackbar)
    snackbar.show()
}