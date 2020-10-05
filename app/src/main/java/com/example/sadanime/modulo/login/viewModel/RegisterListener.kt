package com.example.sadanime.modulo.login.viewModel

interface RegisterListener {
    fun showToask(message:String)
    fun registerSuccess()
    fun registerError()
}