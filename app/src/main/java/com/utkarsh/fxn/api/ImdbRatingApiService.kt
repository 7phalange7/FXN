package com.utkarsh.fxn.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.utkarsh.fxn.BuildConfig
import com.utkarsh.fxn.data.omdb.omdbTitle
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL_OMDB = "https://www.omdbapi.com/?"
const val OMDB_ApiKey = BuildConfig.OMDB_APIKEY

//https://www.omdbapi.com/?i=tt0068646&apikey=$OMDB_ApiKey

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_OMDB)
    .build()

interface ImdbRatingApiService{

    @GET(".")
    fun getOmdbTitle(
        @Query("i") imdb_id : String,
        @Query("apikey") apiKey: String = OMDB_ApiKey
    ) : Deferred<omdbTitle>

    object ImdbRatingApi {

        val retrofitService: ImdbRatingApiService by lazy {
            retrofit.create(ImdbRatingApiService::class.java)
        }
    }

}
