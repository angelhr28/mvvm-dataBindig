package com.example.sadanime.modulo.login.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadanime.helper.application.Constants
import com.example.sadanime.helper.isNullOrEmpty
import com.example.sadanime.modulo.login.model.LoginModel
import com.example.sadanime.root.preferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var email   : String? = null
    var password: String? = null

    lateinit var listener: LoginListener
    private val model = LoginModel()
    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData <Boolean> get() = _progressVisibility

    fun onLoginButtomClick(email :String, password: String ){
        viewModelScope.launch {
            if (isNullOrEmpty(email)){
                listener.showToask("Ingresa un usuario.")
                return@launch
            }

            if (isNullOrEmpty(password)){
                listener.showToask("Ingresa una contraseña.")
                return@launch
            }

            _progressVisibility.value = true

            model.logIn(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    val dataBaseUser = Constants.FIREBASE_DB.reference
                    preferences.deviceToken = Constants.FIREBASE_AUTH.currentUser?.uid
                    dataBaseUser.child("Usuario")
                        .child(preferences.deviceToken ?: "")
                        .addValueEventListener( object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                listener.logInError()
                                listener.showToask("El usuario no existe.")
                                _progressVisibility.value = false
                            }
                            override fun onDataChange(result: DataSnapshot) {
                                if(result.exists()){
                                    preferences.apply {
                                        this.nombre   = result.child("nombres").value.toString()
                                        this.correo   = result.child("correo").value.toString()
                                        this.password = result.child("password").value.toString()
                                    }
                                }
                                listener.logInSuccess()
                                _progressVisibility.value = false
                            }
                        }
                        )
                } else {
                    listener.logInError()
                    listener.showToask("Usuarion o contraseña incorrecta .")
                    _progressVisibility.value = false
                }
            }
        }
    }
}