package com.utkarsh.fxn.ui.iteminfo.tv

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utkarsh.fxn.api.SearchApiService
import com.utkarsh.fxn.data.Result
import com.utkarsh.fxn.data.tmdbTvbyid.tvById
import kotlinx.coroutines.*

class ItemTvViewModel(private val id: Int) : ViewModel() {


    private val _tvInfo = MutableLiveData<tvById>()
    val tvInf: LiveData<tvById>
        get() = _tvInfo

    private val _resultList = MutableLiveData<List<Result>>()
    val resultList: LiveData<List<Result>>
        get() = _resultList


//    // 1-> Imdb  2-> Seasons  3-> Episodes  4-> Runtime
////    private val _iconClicked = MutableLiveData<Int>()
////    val iconClicked: LiveData<Int>
////        get() = _iconClicked

    //you must create your job and coroutineScope before the init block.
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        getTvbyId()
        getSimilarTv()

       // _iconClicked.value = 0
    }

    private fun getTvbyId() {

        var response: String

        coroutineScope.launch {
            var getTvDeferred = SearchApiService.SearchApi.retrofitService.getTvbyId(id)

            try {
                // Await the completion of our Retrofit request
                var tvObject = getTvDeferred.await()
                _tvInfo.value = tvObject

                response = tvObject.name


            } catch (e: Exception) {
                response = "Failure: ${e.message}"
            }

            Log.v("utk", "tv show name is $response")
        }
    }

    private fun getSimilarTv(){

        var response:String

        coroutineScope.launch {
            var getResultDeferred = SearchApiService.SearchApi.retrofitService.getSimiliarTvbyId(id)

            try {
                // Await the completion of our Retrofit request
                var searchResultObject = getResultDeferred.await()
                _resultList.value=searchResultObject.results

                if(searchResultObject.results[0].name!=null)
                    response =searchResultObject.results[0].name
                else
                    response =searchResultObject.results[0].title


            } catch (e: Exception) {
                response = "Failure: ${e.message}"
            }

            Log.v("utk",response)
        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


//    fun logoClicked(i: Int) {
//        _iconClicked.value = i
//    }
//
//    fun logoClickDone() {
//        _iconClicked.value = 0
//    }


}