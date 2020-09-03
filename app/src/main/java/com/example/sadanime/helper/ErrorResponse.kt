package com.example.sadanime.helper

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @field:SerializedName("msj")
    val msj : String? = null,
    @field:SerializedName("status")
    val status : Int? = 0
)