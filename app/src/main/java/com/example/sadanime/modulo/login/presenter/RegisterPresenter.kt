package com.example.sadanime.modulo.login.presenter

import android.util.Log
import com.example.sadanime.modulo.login.model.RegisterModel
import com.example.sadanime.modulo.login.model.pojo.Usuario
import com.example.sadanime.modulo.login.mvp.RegisterMVP
import com.example.sadanime.root.preferences
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.helper.application.Constants.FIREBASE_DB

class RegisterPresenter(private val view: RegisterMVP.View) : RegisterMVP.Presenter {

    private val model = RegisterModel(this)
    private val dataBaseUser = FIREBASE_DB.getReference("Usuario")
    private val TAG  = this.javaClass.toString()

    override fun registerUser(name: String, pass: String, email: String) {
        view.showProgres()
        model.registerUser(email, pass)
            .addOnFailureListener { Log.e(TAG, "registerUser: FALLO EN EL REGISTRO  ${it.message}" ) }
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                val id = FIREBASE_AUTH.currentUser?.uid ?: "0"
                val user = Usuario(id,name, email, pass)
                dataBaseUser.child(id).setValue(user).addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.e(TAG, "registerUser: $name --- $email --- $pass" )
                        preferences.apply {
                            this.nombre   = name
                            this.correo   = email
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
