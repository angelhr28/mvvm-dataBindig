package com.example.sadanime.modulo.principal.model

import com.example.sadanime.helper.getConexionRetrofit
import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import com.example.sadanime.modulo.principal.model.service.LastAnimesService

class PrincipalModel : LastAnimesService.SafeApiRequest() {

    private val TAG = this::class.java.name
    private val retrofit = getConexionRetrofit()
    private val service  = retrofit.create(LastAnimesService::class.java)

    suspend fun getListAnimeEstreno(): LatestAnimes {
        return apiRequest {
            service.getLastAnimes()
        }
    }

}
