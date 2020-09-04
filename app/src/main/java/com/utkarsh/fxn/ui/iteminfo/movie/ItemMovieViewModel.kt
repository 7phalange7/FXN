package com.utkarsh.fxn.ui.iteminfo.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utkarsh.fxn.api.ImdbRatingApiService
import com.utkarsh.fxn.api.SearchApiService
import com.utkarsh.fxn.data.Result
import com.utkarsh.fxn.data.omdb.omdbTitle
import com.utkarsh.fxn.data.tmdbMoviebyId.movieById
import com.utkarsh.fxn.data.tmdbTvbyid.tvById
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemMovieViewModel(private val id: Int) : ViewModel() {


    private val _movieInfo = MutableLiveData<movieById>()
    val movieInf: LiveData<movieById>
        get() = _movieInfo

    private val _resultList = MutableLiveData<List<Result>>()
    val resultList: LiveData<List<Result>>
        get() = _resultList

    private val _omdbMovie = MutableLiveData<omdbTitle>()
    val omdbMovie: LiveData<omdbTitle>
        get() = _omdbMovie

    private val _rottenScore = MutableLiveData<String>()
    val rottenScore: LiveData<String>
        get() = _rottenScore

    private val _metaScore = MutableLiveData<String>()
    val metaScore: LiveData<String>
        get() = _metaScore



    //you must create your job and coroutineScope before the init block.
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        getMoviebyId()

        getSimilarMovie()
    }


    private fun getMoviebyId() {

        var response: String

        coroutineScope.launch {
            var getMovieDeferred = SearchApiService.SearchApi.retrofitService.getMoviebyId(id)

            try {
                // Await the completion of our Retrofit request
                var movieObject = getMovieDeferred.await()
                _movieInfo.value = movieObject

                response = movieObject.imdbId!!

                getMovieRatings()


            } catch (e: Exception) {
                response = "Failure: ${e.message}"
            }

            Log.v("utk", "movie name is $response")
        }

    }

    private fun getMovieRatings() {

        val imdbId : String = _movieInfo.value?.imdbId + ""

        Log.v("utk", "Imdb Id - : ${imdbId}")
        coroutineScope.launch {


            var getOmdbTitleDeferred =
                ImdbRatingApiService.ImdbRatingApi.retrofitService.getOmdbTitle(imdbId)
            try {
                var omdbTitleObject = getOmdbTitleDeferred.await()

                if(omdbTitleObject.ratings[1].source!=null)
                if(omdbTitleObject.ratings[1].source!="Rotten Tomatoes")
                    omdbTitleObject.ratings[1].value=null

                _omdbMovie.value = omdbTitleObject




            } catch (e: java.lang.Exception) {
                Log.v("utk", "Failure in omdb api: ${e.message}")
            }


        }

    }

    private fun getSimilarMovie() {

        var response: String

        coroutineScope.launch {
            var getResultDeferred =
                SearchApiService.SearchApi.retrofitService.getSimiliarMoviebyId(id)

            try {
                // Await the completion of our Retrofit request
                var searchResultObject = getResultDeferred.await()
                _resultList.value = searchResultObject.results


                response = searchResultObject.results[0].title


            } catch (e: Exception) {
                response = "Failure: ${e.message}"
            }

            Log.v("utk", response)
        }


    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}

