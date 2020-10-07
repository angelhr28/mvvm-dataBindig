package com.example.sadanime.modulo.principal.model.service

import com.example.sadanime.helper.ErrorResponse
import com.example.sadanime.helper.application.Constants.API_GENERAL
import com.example.sadanime.helper.application.Constants.LASTER_ANIME
import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import java.io.IOException

interface LastAnimesService {

    @GET(API_GENERAL + LASTER_ANIME)
    suspend fun getLastAnimes(): Response<LatestAnimes>

    abstract class SafeApiRequest{

        suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>) : T {
            val response = call.invoke()

            if ( response.isSuccessful ) {
                return response.body()!!
            }

            val error = response.errorBody()?.string()
            var result = ErrorResponse()
            val messageE = StringBuilder()
            error?.let {
                try {
                    val jObjError = JSONObject(error)
                    result = Gson().fromJson(jObjError.toString(), ErrorResponse::class.java)
                }catch (e: JSONException){
                    result.msj = "Error front $e"
                }
            }
            result.msj = "Error Code ${response.code()}"
            messageE.append(result.msj)
            throw ApiException(messageE.toString())
        }
    }

    class ApiException(resultE:String): IOException(resultE)

}