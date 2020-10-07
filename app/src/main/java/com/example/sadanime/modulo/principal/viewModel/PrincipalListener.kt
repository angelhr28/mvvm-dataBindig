package com.example.sadanime.modulo.principal.viewModel

import com.example.sadanime.modulo.principal.model.pojo.AnimesItem

interface PrincipalListener {
    fun showEmpty(msj: String)
    fun hideEmpty()
    fun showSkeleton()
    fun hideSkeleton()
    fun getListAnimeEstreno(animes: List<AnimesItem?>?)
}