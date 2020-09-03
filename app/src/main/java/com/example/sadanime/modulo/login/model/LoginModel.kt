package com.example.sadanime.modulo.login.model

import com.example.sadanime.modulo.login.mvp.LoginMVP
import com.example.sadanime.modulo.login.presenter.LoginPresenter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH

class LoginModel(private val presenter: LoginPresenter) : LoginMVP.Model {

    override fun logIn(user: String, password: String) : Task<AuthResult> {
        return FIREBASE_AUTH.signInWithEmailAndPassword(user, password)
    }

}
