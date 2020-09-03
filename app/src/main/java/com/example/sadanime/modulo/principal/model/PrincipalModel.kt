package com.example.sadanime.modulo.principal.model

import android.content.ContentValues.TAG
import android.util.Log
import com.example.sadanime.helper.getConexionRetrofit
import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import com.example.sadanime.modulo.principal.model.service.LastAnimesService
import com.example.sadanime.modulo.principal.mvp.PrincipalMVP
import com.example.sadanime.modulo.principal.presenter.PrincipalPresenter
import com.example.sadanime.root.ctx
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import java.io.IOException

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
