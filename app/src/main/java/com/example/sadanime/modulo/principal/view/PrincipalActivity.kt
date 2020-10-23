package com.example.sadanime.modulo.principal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.sadanime.R
import com.example.sadanime.databinding.ActivityPrincipalBinding
import com.example.sadanime.modulo.login.view.LoginActivity
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.root.preferences
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.modulo.principal.viewModel.PrincipalListener
import com.example.sadanime.modulo.principal.viewModel.PrincipalViewModel

class PrincipalActivity : AppCompatActivity(), PrincipalListener,
    AnimesAdapter.OnCardClickListener {

    private val TAG = this::class.java.name

    private lateinit var _viewModel: PrincipalViewModel
    private lateinit var binding   : ActivityPrincipalBinding
    private lateinit var mAdapter  : AnimesAdapter
    private lateinit var skeleton  : RecyclerViewSkeletonScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(rootPrincipal)
            _viewModel = ViewModelProvider(this@PrincipalActivity).get()
            _viewModel.listener = this@PrincipalActivity
            viewmodel = _viewModel

            btnSingout.setOnClickListener {
                FIREBASE_AUTH.signOut()
                preferences.clear()
                val intent = Intent(this@PrincipalActivity,  LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            mAdapter = AnimesAdapter(this@PrincipalActivity)

            rcvLastAnime.apply {
                layoutManager = LinearLayoutManager(this@PrincipalActivity)
                setHasFixedSize(false)
            }

            _viewModel.getListAnimeEstreno()
            lifecycleOwner = this@PrincipalActivity
        }
    }

    override fun getListAnimeEstreno(animes: List<AnimesItem?>?) {
//        Log.e(TAG, "getListAnimeEstreno: $animes")
//        mAdapter = AnimesAdapter( animes,  this@PrincipalActivity)
        binding.rcvLastAnime.adapter =  mAdapter
        mAdapter.setData(animes)
    }

    override fun showEmpty(msj: String) {

    }

    override fun hideEmpty() {

    }

    override fun showSkeleton() {
        skeleton =  Skeleton.bind(binding.rcvLastAnime)
            .shimmer(true)
            .angle(20)
            .color(R.color.colorShimmerSkeleton)
            .duration(1200)
            .load ( R.layout.skeleton_item_anime)
            .show()
    }

    override fun hideSkeleton() {
        skeleton.hide()
    }

    override fun onAnimeSelect(data: AnimesItem) {


    }
}
