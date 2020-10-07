package com.example.sadanime.modulo.principal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.sadanime.databinding.ActivityPrincipalBinding
import com.example.sadanime.modulo.login.view.LoginActivity
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.root.preferences
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.modulo.principal.viewModel.PrincipalListener
import com.example.sadanime.modulo.principal.viewModel.PrincipalViewModel

class PrincipalActivity : AppCompatActivity(), PrincipalListener {

    private val TAG = this::class.java.name

    private lateinit var _viewModel    : PrincipalViewModel
    private lateinit var binding       : ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(rootPrincipal)
            _viewModel = ViewModelProvider(this@PrincipalActivity).get()
            _viewModel.listener = this@PrincipalActivity
            viewmodel = _viewModel
            _viewModel.getListAnimeEstreno()

            lifecycleOwner = this@PrincipalActivity
            btnSingout.setOnClickListener {
                FIREBASE_AUTH.signOut()
                preferences.clear()
                val intent = Intent(this@PrincipalActivity,  LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    override fun getListAnimeEstreno(animes: List<AnimesItem?>?) {

        Log.e(TAG, "getListAnimeEstreno: $animes" )
    }

    override fun showEmpty(msj: String) {

    }

    override fun hideEmpty() {

    }

    override fun showSkeleton() {

    }

    override fun hideSkeleton() {

    }
}
