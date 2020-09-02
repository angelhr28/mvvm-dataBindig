package com.example.platform_univ.modulo.login.presenter

import android.util.Log
import com.example.platform_univ.modulo.login.model.RegisterModel
import com.example.platform_univ.modulo.login.model.pojo.Usuario
import com.example.platform_univ.modulo.login.mvp.RegisterMVP
import com.example.platform_univ.root.preferences
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_AUTH
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_DB

class RegisterPresenter(private val view: RegisterMVP.View) : RegisterMVP.Presenter {

    private val model = RegisterModel(this)
    private val dataBaseUser = FIREBASE_DB.getReference("Usuario")
    private val TAG  = this.javaClass.toString()

    override fun registerUser(name: String, pass: String, ape: String, phone: String, email: String) {
        view.showProgres()
        model.registerUser(email, pass)
            .addOnFailureListener { Log.e(TAG, "registerUser: FALLO EN EL REGISTRO  ${it.message}" ) }
            .addOnCompleteListener {
            if(it.isSuccessful){
                val id = FIREBASE_AUTH.currentUser?.uid ?: "0"
                val user = Usuario(id,name, ape, phone, email, pass)
                dataBaseUser.child(id).setValue(user).addOnCompleteListener {
                    if(it.isSuccessful) {
                        preferences.apply {
                            this.nombre   = name
                            this.phone    = phone
                            this.correo   = email
                            this.apellido = ape
                            this.password = pass
                        }
                        view.registerSuccess()
                    }else {
                        view.registerError()
                        view.showToask("fallo al insertar el usuario")
                    }
                    view.hideProgres()
                }
            } else {
                view.registerError()
                view.showToask("Fallo en conexion a firebase.")
            }
        }
    }
}
