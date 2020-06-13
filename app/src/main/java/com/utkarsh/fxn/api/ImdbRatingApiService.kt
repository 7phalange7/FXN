package com.utkarsh.fxn.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.utkarsh.fxn.data.omdb.omdbTitle
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val BASE_URL_OMDB = "https://www.omdbapi.com/?"

//https://www.omdbapi.com/?i=tt0068646&apikey=47f06f90

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_OMDB)
    .build()

interface ImdbRatingApiService{

    @GET(".")
    fun getOmdbTitle(
        @Query("i") imdb_id : String,
        @Query("apikey") apiKey: String ="47f06f90"
    ) : Deferred<omdbTitle>

    object ImdbRatingApi {

        val retrofitService: ImdbRatingApiService by lazy {
            retrofit.create(ImdbRatingApiService::class.java)
        }
    }

}
