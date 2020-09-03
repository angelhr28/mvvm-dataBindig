package com.example.sadanime.modulo.login.model

import com.example.sadanime.modulo.login.mvp.RegisterMVP
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.example.sadanime.helper.application.Constants

class RegisterModel(val registerPresenter: RegisterMVP.Presenter): RegisterMVP.Model {

    override fun registerUser(user: String, password: String): Task<AuthResult> {
        return Constants.FIREBASE_AUTH.createUserWithEmailAndPassword(user, password)
    }
}
