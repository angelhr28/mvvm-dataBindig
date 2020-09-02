package com.example.sadanime.root

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.sadanime.helper.application.MySharedPreferences

val preferences       : MySharedPreferences by lazy { MyApp.prefs!! }
lateinit var ctx      : Context

class MyApp: Application(){

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        prefs = MySharedPreferences(applicationContext)
        ctx = this
    }
}
