package com.example.sadanime.modulo.login.viewModel

interface LoginListener {
    fun showToask(message:String)
    fun logInSuccess()
    fun logInError()
}