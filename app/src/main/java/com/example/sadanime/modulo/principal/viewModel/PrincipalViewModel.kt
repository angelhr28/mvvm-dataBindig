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

    private val _latestAnimes = MutableLiveData<List<AnimesItem?>?>()
    val latestAnimes: LiveData<List<AnimesItem?>?> get() = _latestAnimes

    private val _isSkeleton = MutableLiveData<Boolean>()
    val isSkeleton: LiveData<Boolean> get() = _isSkeleton

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _snackBar = MutableLiveData<String>()
    val snackBar: LiveData<String> get() = _snackBar

    fun getListAnimeEstreno() {

        _isSkeleton.value = true
        viewModelScope.launch {
            try {
                val response = model.getListAnimeEstreno()
                response.let {
                    if (it.animes?.count() ?: 0 > 0){
                        _isSkeleton.value = false
                        _latestAnimes.value = it.animes
                    } else {
                        _isSkeleton.value = true
                    }
                    return@launch
                }
            } catch (e: LastAnimesService.ApiException) {
                _isSkeleton.value = false
                _isEmpty.value = true
                _snackBar.value = e.message ?: "Hubo un error"
            }
        }
    }
}
