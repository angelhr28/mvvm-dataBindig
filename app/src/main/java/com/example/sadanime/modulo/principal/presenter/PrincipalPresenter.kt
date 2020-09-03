package com.example.sadanime.modulo.principal.presenter

import android.util.Log
import com.example.sadanime.R
import com.example.sadanime.helper.ErrorResponse
import com.example.sadanime.modulo.principal.model.PrincipalModel
import com.example.sadanime.modulo.principal.mvp.PrincipalMVP
import com.example.sadanime.root.ctx
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import retrofit2.HttpException


class PrincipalPresenter(val view : PrincipalMVP.View):PrincipalMVP.Presenter {

    private val TAG = this::class.java.name
    private val compositeDisposable = CompositeDisposable()
    private var model = PrincipalModel(this)

    override fun getListAnimeEstreno() {

        view.showSkeleton()
        val disposable = model.getListAnimeEstreno().subscribe(
            { result ->
                if(result.animes?.size ?: 0 > 0 ){
                    view.getListAnimeEstreno( result.animes )
                } else {
                    view.showEmpty(ctx.getString(R.string.msj_empty))
                }
                view.hideSkeleton()
            },
            { error ->
                if (error is HttpException) {
                    val response = error.response()
                    try {
                        response.errorBody()?.string()?.let {
                            val jObjError = JSONObject(it)
                            val result = Gson().fromJson(jObjError.toString(), ErrorResponse::class.java)
                            Log.e(TAG, result.msj ?: "")
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }
                } else {
                    Log.e(TAG, error.message.toString())
                }
            },
            {}
        )
        compositeDisposable.add(disposable)
    }

    override fun destroy() {
        if (!compositeDisposable.isDisposed){
            try {
                compositeDisposable.dispose()
            }catch (e:Exception){
                Log.e(TAG,"Error en destroy")
            }
        }
    }

}
