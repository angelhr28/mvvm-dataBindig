package com.example.sadanime.helper

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @field:SerializedName("msj")
    var msj : String? = null,
    @field:SerializedName("status")
    var status : Int? = 0
)