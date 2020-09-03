package com.example.sadanime.modulo.principal.model.service

import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import io.reactivex.Observable
import com.example.sadanime.helper.application.Constants.API_GENERAL
import com.example.sadanime.helper.application.Constants.LASTER_ANIME
import retrofit2.http.GET

interface LastAnimesService {

//    @GET(API_GENERAL + LASTER_ANIME + "{page}")
//    fun getLastAnimes( @Path("page") page: String): Flowable<LatestAnimes>

    @GET(API_GENERAL + LASTER_ANIME)
    fun getLastAnimes(): Observable<LatestAnimes>


}