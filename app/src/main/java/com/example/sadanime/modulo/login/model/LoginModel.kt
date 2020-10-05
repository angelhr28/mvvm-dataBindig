package com.example.sadanime.modulo.login.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH

class LoginModel(){

    fun logIn(user: String, password: String) : Task<AuthResult> {
        return FIREBASE_AUTH.signInWithEmailAndPassword(user, password)
    }

}
