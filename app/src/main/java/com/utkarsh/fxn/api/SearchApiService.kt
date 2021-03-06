package com.utkarsh.fxn.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.utkarsh.fxn.BuildConfig
import com.utkarsh.fxn.data.SearchResult
import com.utkarsh.fxn.data.extIdMovie
import com.utkarsh.fxn.data.extIdTv
import com.utkarsh.fxn.data.tmdbMoviebyId.movieById
import com.utkarsh.fxn.data.tmdbTvbyid.tvById
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.themoviedb.org/3/search/multi?api_key=$TMDB_ApiKey&language=en-US&query=paatal%20lok&page=1&include_adult=false

const val BASE_URL_TMDB= "https://api.themoviedb.org/3/"
const val TMDB_ApiKey = BuildConfig.TMDB_APIKEY

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_TMDB)
    .build()

interface SearchApiService {

    @GET("search/multi?api_key=$TMDB_ApiKey&language=en-US&")
    fun searchForTitle(
        @Query("query") title: String,
        @Query("page") pageIndex: Int = 1,
        @Query("include_adult") tf: String = "false"

    ): Deferred<SearchResult>

    @GET("search/movie?api_key=$TMDB_ApiKey&language=en-US&")
    fun searchForMovies(
        @Query("query") title: String,
        @Query("include_adult") tf: String = "false"

    ): Deferred<SearchResult>

    @GET("search/tv?api_key=$TMDB_ApiKey&language=en-US&")
    fun searchForTv(
        @Query("query") title: String,
        @Query("include_adult") tf: String = "false"

    ): Deferred<SearchResult>

    @GET("movie/{id}/external_ids?api_key=$TMDB_ApiKey")
    fun getImdbIdMovie(
        @Path("id") tmdbId: Int
    ) : Deferred<extIdMovie>

    @GET("tv/{id}/external_ids?api_key=$TMDB_ApiKey&language=en-US")
    fun getImdbIdTv(
        @Path("id") tmdbId: Int
    ) : Deferred<extIdTv>

    @GET("tv/{id}?api_key=$TMDB_ApiKey&language=en-US")
    fun getTvbyId(
        @Path("id") tmdbId: Int
    ) : Deferred<tvById>

    @GET("movie/{id}?api_key=$TMDB_ApiKey&language=en-US")
    fun getMoviebyId(
        @Path("id") tmdbId: Int
    ) : Deferred<movieById>

    @GET("tv/{id}/similar?api_key=$TMDB_ApiKey&language=en-US")
    fun getSimiliarTvbyId(
        @Path("id") tmdbId: Int
    ) : Deferred<SearchResult>

    @GET("movie/{id}/similar?api_key=$TMDB_ApiKey&language=en-US")
    fun getSimiliarMoviebyId(
        @Path("id") tmdbId: Int
    ) : Deferred<SearchResult>





    object SearchApi {

        val retrofitService: SearchApiService by lazy {
            retrofit.create(SearchApiService::class.java)
        }
    }
}
