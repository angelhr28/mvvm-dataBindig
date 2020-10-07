package com.example.sadanime.modulo.principal.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadanime.modulo.principal.model.PrincipalModel
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.modulo.principal.model.service.LastAnimesService
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrincipalViewModel : ViewModel(){

    private val TAG = this::class.java.name
    private var model = PrincipalModel()

    private val _latestAnimes = MutableLiveData<List<AnimesItem?>>()
    val latestAnimes: LiveData<List<AnimesItem?>> get() = _latestAnimes
    lateinit var listener: PrincipalListener

    fun getListAnimeEstreno() {

        listener.showSkeleton()
        viewModelScope.launch {
            try {
                val response = model.getListAnimeEstreno()
                Log.e(TAG, "getListAnimeEstreno: ${response.animes}" )
                response?.let {
                    listener.getListAnimeEstreno(it.animes)
                    listener.hideSkeleton()
                    return@launch
                }
                listener.hideSkeleton()
                listener.hideEmpty()
            } catch (e: LastAnimesService.ApiException) {
                Log.e(TAG, "getListAnimeEstreno: $e" )
                listener.hideSkeleton()
                listener.hideEmpty()
            }
        }
    }




}
