package com.example.sadanime.helper.application

import android.content.Context

class MySharedPreferences(context: Context) {

    private val fileName = "SadAnime_preferences"

    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var deviceToken: String?
        get() = prefs.getString("device_token", "")
        set(value) = prefs.edit().putString("device_token", value).apply()
    var correo: String?
        get() = prefs.getString("correo", "")
        set(value) = prefs.edit().putString("correo", value).apply()
    var phone: String?
        get() = prefs.getString("phone", "")
        set(value) = prefs.edit().putString("phone", value).apply()
    var nombre: String?
        get() = prefs.getString("nombre", "")
        set(value) = prefs.edit().putString("nombre", value).apply()
    var apellido: String?
        get() = prefs.getString("apellido", "")
        set(value) = prefs.edit().putString("apellido", value).apply()
    var password: String?
            get() = prefs.getString("password", "")
            set(value) = prefs.edit().putString("password", value).apply()

    fun clear(){
        prefs.edit().remove("device_token").apply()
        prefs.edit().remove("correo").apply()
        prefs.edit().remove("phone").apply()
        prefs.edit().remove("nombre").apply()
        prefs.edit().remove("apellido").apply()
        prefs.edit().remove("password").apply()

    }
}
