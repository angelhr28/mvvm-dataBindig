package com.example.sadanime.modulo.login.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.sadanime.R
import com.example.sadanime.databinding.ActivityRegisterUserBinding
import com.example.sadanime.helper.application.Constants.FIREBASE_STORAGE
import com.example.sadanime.helper.desing.CircleTransform
import com.example.sadanime.helper.setCircularImage
import com.example.sadanime.modulo.login.viewModel.RegisterListener
import com.example.sadanime.modulo.login.viewModel.RegisterViewModel
import com.example.sadanime.modulo.principal.view.PrincipalActivity
import com.example.sadanime.root.ctx
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso


class RegisterUserActivity : AppCompatActivity(), RegisterListener {

    private val TAG = this.javaClass.toString()
    private var uriImage     : Uri? = null
    private val strorageRefP : StorageReference= FIREBASE_STORAGE.getReference("images")

    private lateinit var _viewModel : RegisterViewModel
    private lateinit var binding    : ActivityRegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(rootRegister)
            _viewModel = ViewModelProvider(this@RegisterUserActivity).get()
            _viewModel.listener = this@RegisterUserActivity
            viewmodel = _viewModel
            lifecycleOwner = this@RegisterUserActivity

            Picasso.get()
                .load(R.drawable.empty_user)
                .transform(CircleTransform())
                .into(binding.imgFotoRegist)

            btnAddImg.setOnClickListener {
                fileChooser()
            }
        }
    }

    private fun fileUploader() {
        val ref = strorageRefP.child("${System.currentTimeMillis()}_${(0..10000).random()}.jpeg"  )

        uriImage?.let {
            ref.putFile(it)
                .addOnFailureListener {
                    Log.e(TAG, "fileUploader: error update ${it.message}" )
                }
                .addOnSuccessListener { taskSnapshot -> // Get a URL to the uploaded content
                    val uri = taskSnapshot.storage.downloadUrl
                    if (uri.isComplete){
//                        while (!uri.isComplete){} //// todos se corto
                        val url = uri.result
                        Log.e(TAG,"url $url")
                        showToask("se subio revisa prro :v")
                    }
                }
        }
    }

    private fun fileChooser(){
        val intent = Intent()
        intent.apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            data.data?.let {
                uriImage = it
                Picasso.get()
                    .load(uriImage)
                    .placeholder(setCircularImage(ctx, R.drawable.empty_user))
                    .error(setCircularImage(ctx, R.drawable.empty_user))
                    .transform(CircleTransform())
                    .into(binding.imgFotoRegist)
            }

        }
    }

    override fun showToask(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun registerSuccess() {
        fileUploader()
        val intent = Intent(this,  PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun registerError() {
        binding.apply {
            edtNameUser.setText("")
            edtPassUser.setText("")
            edtEmailUser.setText("")
            edtNameUser.clearFocus()
            edtPassUser.clearFocus()
            edtEmailUser.clearFocus()
        }
    }


}
