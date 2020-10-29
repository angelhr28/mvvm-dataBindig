package com.example.sadanime.modulo.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.sadanime.databinding.ActivityLoginBinding
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.modulo.login.viewModel.LoginListener
import com.example.sadanime.modulo.login.viewModel.LoginViewModel
import com.example.sadanime.modulo.principal.view.PrincipalActivity
import com.example.sadanime.root.UnsafeOkHttpClient

class LoginActivity : AppCompatActivity(), LoginListener {

    private val TAG = this::class.java.name
    private lateinit var _viewModel    : LoginViewModel
    private lateinit var binding       : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        UnsafeOkHttpClient.initializeSSLContext(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(rootLogin)
            _viewModel = ViewModelProvider(this@LoginActivity).get()
            _viewModel.listener = this@LoginActivity
            viewmodel = _viewModel
            lifecycleOwner = this@LoginActivity

            lblCreateUser.setOnClickListener {
                val intent = Intent(this@LoginActivity,  RegisterUserActivity::class.java)
                startActivity(intent)
//                finish()
            }
        }

    }

    override fun showToask(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}

    override fun logInSuccess() {
        val intent = Intent(this,  PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun logInError() {
        binding.apply {
            edtUser.setText("")
            edtPass.setText("")
            edtUser.clearFocus()
            edtPass.clearFocus()
        }
    }

    override fun onStart() {
        super.onStart()
        if (FIREBASE_AUTH.currentUser != null){
            val intent = Intent(this,  PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }
        
    }

}
