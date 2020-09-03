package com.example.sadanime.modulo.principal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.sadanime.R
import com.example.sadanime.modulo.login.view.LoginActivity
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.modulo.principal.mvp.PrincipalMVP
import com.example.sadanime.modulo.principal.presenter.PrincipalPresenter
import com.example.sadanime.root.preferences
import kotlinx.android.synthetic.main.activity_perfil.*
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH

class PrincipalActivity : AppCompatActivity(), PrincipalMVP.View {

    private val TAG = this::class.java.name

    private var lblName     : TextView? = null
    private var lblApellido : TextView? = null
    private var lblCorreo   : TextView? = null
    private var lblPhone    : TextView? = null
    private var btnSingout  : Button?   = null
    private var presenter   : PrincipalMVP.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        presenter = PrincipalPresenter(this)

        lblName     = lbl_name
        lblApellido = lbl_apellido
        lblCorreo   = lbl_correo
        lblPhone    = lbl_phone
        btnSingout  = btn_singout

        lblName?.text     = preferences.nombre
        lblApellido?.text = preferences.apellido
        lblCorreo?.text   = preferences.correo
        lblPhone?.text    = preferences.phone

        btnSingout?.setOnClickListener {
            FIREBASE_AUTH.signOut()
            preferences.clear()
            val intent = Intent(this,  LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        presenter?.getListAnimeEstreno()

    }

    override fun getListAnimeEstreno(ListAnimes: List<AnimesItem?>?) {
        Log.e(TAG, "getListAnimeEstreno: $ListAnimes" )
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
