package com.example.sadanime.modulo.principal.mvp

import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import io.reactivex.Observable

interface PrincipalMVP {

    interface View {
        fun getListAnimeEstreno(ListAnimes: List<AnimesItem?>?)
        fun showEmpty(msj: String)
        fun hideEmpty()
        fun showSkeleton()
        fun hideSkeleton()
    }

    interface Presenter{
        fun getListAnimeEstreno()
        fun destroy()
    }

    interface Model{
        fun getListAnimeEstreno(): Observable<LatestAnimes>
    }

}
