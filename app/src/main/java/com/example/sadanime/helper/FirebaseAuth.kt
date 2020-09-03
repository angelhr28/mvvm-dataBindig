package com.example.sadanime.helper

import com.google.firebase.auth.FirebaseAuth
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH

class Auth private constructor() {

    companion object {
    private var instance: FirebaseAuth? = null
        fun getInstance(): FirebaseAuth {
            if (instance == null) instance = FIREBASE_AUTH
            return instance!!
        }
    }
}
