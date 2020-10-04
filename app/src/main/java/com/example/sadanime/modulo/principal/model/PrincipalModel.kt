package com.example.sadanime.modulo.principal.model

import com.example.sadanime.helper.getConexionRetrofit
import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import com.example.sadanime.modulo.principal.model.service.LastAnimesService
import com.example.sadanime.modulo.principal.mvp.PrincipalMVP
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PrincipalModel(val principalPresenter: PrincipalMVP.Presenter): PrincipalMVP.Model {

    private val TAG = this::class.java.name
    private val retrofit = getConexionRetrofit()
    private val service  = retrofit.create(LastAnimesService::class.java)

    override fun getListAnimeEstreno(): Observable<LatestAnimes> {
        return service.getLastAnimes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
