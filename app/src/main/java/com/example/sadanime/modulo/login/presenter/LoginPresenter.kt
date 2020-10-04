package com.example.sadanime.modulo.login.presenter

import com.example.sadanime.modulo.login.model.LoginModel
import com.example.sadanime.modulo.login.mvp.LoginMVP
import com.example.sadanime.root.preferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.helper.application.Constants.FIREBASE_DB

class LoginPresenter( private val view: LoginMVP.View ): LoginMVP.Presenter {

    private val model = LoginModel(this)

    override fun logIn(user: String, password: String) {

        if (user.isEmpty()){
            view.showToask("Ingresa un usuario.")
            return
        }

        if (password.isEmpty()){
            view.showToask("Ingresa una contraseña.")
            return
        }

        view.showProgres()

        model.logIn(user, password).addOnCompleteListener {
            if(it.isSuccessful){
                val dataBaseUser = FIREBASE_DB.reference
                preferences.deviceToken = FIREBASE_AUTH.currentUser?.uid
                dataBaseUser.child("Usuario")
                            .child(preferences.deviceToken ?: "")
                            .addValueEventListener( object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError) {
                                        view.logInError()
                                        view.showToask("El usuario no existe.")
                                        view.hideProgres()
                                    }
                                    override fun onDataChange(result: DataSnapshot) {
                                        if(result.exists()){
                                            preferences.apply {
                                                this.nombre   = result.child("nombres").value.toString()
                                                this.correo   = result.child("correo").value.toString()
                                                this.password = result.child("password").value.toString()
                                            }
                                        }
                                        view.logInSuccess()
                                        view.hideProgres()
                                    }
                                }
                            )
            } else {
                view.logInError()
                view.showToask("Usuarion o contraseña incorrecta .")
                view.hideProgres()
            }
        }

    }
}
