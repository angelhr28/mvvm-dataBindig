package com.example.sadanime.modulo.principal.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.sadanime.R
import com.example.sadanime.databinding.ActivityPrincipalBinding
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.helper.showSimpleSnackbar
import com.example.sadanime.modulo.login.view.LoginActivity
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.modulo.principal.view.fragment.RecentAnimeFragment
import com.example.sadanime.modulo.principal.viewModel.PrincipalListener
import com.example.sadanime.modulo.principal.viewModel.PrincipalViewModel
import com.example.sadanime.root.preferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable


class PrincipalActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = this::class.java.name

    private lateinit var _viewModel: PrincipalViewModel
    private lateinit var binding   : ActivityPrincipalBinding

    private var fragmentTAG  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(rootPrincipal)
            _viewModel = ViewModelProvider(this@PrincipalActivity).get()
            viewmodel = _viewModel

            lifecycleOwner = this@PrincipalActivity
            btnSingout.setOnClickListener {
                FIREBASE_AUTH.signOut()
                preferences.clear()
                val intent = Intent(this@PrincipalActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            val radius = resources.getDimension(R.dimen.bottom_sheet_corner_radius)

            val bottomBarBackground = bappPrincipal.background as MaterialShapeDrawable
            bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
                .toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

            bnvPrincipal.setupBottomNavigation()

            _viewModel.snackBar.observe(this@PrincipalActivity,{
                rootPrincipal.showSimpleSnackbar(it){}
            })

        }
    }

    private fun BottomNavigationView.setupBottomNavigation() {
        this.apply {
            setOnNavigationItemSelectedListener(this@PrincipalActivity)
            selectedItemId = menu.getItem(1).itemId
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var titulo = ""
        val transaction = supportFragmentManager.beginTransaction()

        fragmentTAG = RecentAnimeFragment::class.java.name
        transaction.replace(R.id.fg_princial, RecentAnimeFragment.newInstance(), fragmentTAG)
            .commit()

        binding.tbPrincipal .title = titulo
        return true
    }
}
