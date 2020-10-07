package com.example.sadanime.modulo.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadanime.helper.application.Constants
import com.example.sadanime.helper.isNullOrEmpty
import com.example.sadanime.modulo.login.model.RegisterModel
import com.example.sadanime.modulo.login.model.pojo.Usuario
import com.example.sadanime.root.preferences
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    var name    : String? = null
    var email   : String? = null
    var password: String? = null

    lateinit var listener: RegisterListener
    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> get() = _progressVisibility

    private val model = RegisterModel()
    private val dataBaseUser = Constants.FIREBASE_DB.getReference("Usuario")
    private val TAG  = this.javaClass.toString()

    fun onRegistButtomClick(name: String, pass: String, email: String) {
        viewModelScope.launch {
            if (isNullOrEmpty(name)){
                listener.showToask("Ingresa un nombre.")
                return@launch
            }

            if (isNullOrEmpty(email)){
                listener.showToask("Ingresa un correo.")
                return@launch
            }

            if (isNullOrEmpty(pass)){
                listener.showToask("Ingresa una contraseña.")
                return@launch
            }

            _progressVisibility.value = true
            model.registerUser(email, pass)
                .addOnFailureListener { Log.e(TAG, "registerUser: FALLO EN EL REGISTRO  ${it.message}" ) }
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val id = Constants.FIREBASE_AUTH.currentUser?.uid ?: "0"
                        val user = Usuario(id,name, email, pass)
                        dataBaseUser.child(id).setValue(user).addOnCompleteListener {
                            if(it.isSuccessful) {
                                Log.e(TAG, "registerUser: $name --- $email --- $pass" )
                                preferences.apply {
                                    this.nombre   = name
                                    this.correo   = email
                                    this.password = pass
                                }
                                listener.registerSuccess()
                            }else {
                                listener.registerError()
                                listener.showToask("fallo al insertar el usuario")
                            }
                            _progressVisibility.value = false
                        }
                    } else {
                        listener.registerError()
                        listener.showToask("Fallo en conexion a firebase.")
                        _progressVisibility.value = false
                    }
                }

        }

    }
}