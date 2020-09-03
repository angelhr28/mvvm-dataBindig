package com.example.sadanime.helper.application

import android.graphics.Color
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

object Constants {
    val FIREBASE_AUTH = FirebaseAuth.getInstance()
    val FIREBASE_DB = FirebaseDatabase.getInstance()
    val FIREBASE_STORAGE = FirebaseStorage.getInstance()

    //END POINTS

//    'latestAnimeAdded': '/api/v1/latestAnimes',
//    'getAnimeOvas': '/api/v1/ovas/[:page]',
//    'getAnimeMovies': '/api/v1/movies/[:page]',
//    'getAnimesByGender': '/api/v1/genres/[:genre]/[:page]',
//    'getAnimesListByLetter': '/api/v1/letter/[:letter]/[:page]',
//    'searchAnime': '/api/v1/search/[:title]',
//    'getAnimeVideoByServer': '/api/v1/video/[:id]/[:chapter]',
//    'schedule': '/api/v1/schedule/[:day]'

    const val ROOT_LOCAL  = "http://192.168.1.17:3000/"
    const val API_GENERAL = "api/v1/"

    const val LASTER_ANIME   = "latestAnimes"
    const val OVAS_ANIME     = "ovas/[:page]"
    const val MOVIES_ANIME   = "movies/[:page]"
    const val GENEROS_ANIME  = "genres/[:genre]/[:page]"
    const val LETTER_ANIME   = "letter/[:letter]/[:page]"
    const val SEARCH_ANIME   = "search/[:title]"
    const val VIDEO_ANIME    = "video/[:id]/[:chapter]"
    const val CALENDAR_ANIME = "schedule/[:day]"

}
