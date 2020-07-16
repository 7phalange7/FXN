package com.utkarsh.fxn.ui.searchresult

//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.utkarsh.fxn.api.SearchApiService
//import com.utkarsh.fxn.api.SearchApiService.SearchApi
//import com.utkarsh.fxn.data.Result
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//
//class SearchResultViewModel(private val searchTitle: String) : ViewModel() {
//
//    private val _resultList = MutableLiveData<List<Result>>()
//    val resultList: LiveData<List<Result>>
//        get() = _resultList
//
//    //you must create your job and coroutineScope before the init block.
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(Dispatchers.Main +viewModelJob)
//
//    init {
//
//        getSearchResult()
//
//    }
//
//    private fun getSearchResult(){
//
//        var response:String
//
//        coroutineScope.launch {
//           var getResultDeferred = SearchApi.retrofitService.searchForMovies(searchTitle)
//
//            try {
//                // Await the completion of our Retrofit request
//                var searchResultObject = getResultDeferred.await()
//                _resultList.value=searchResultObject.results
//
//                if(searchResultObject.results[0].name!=null)
//                response =searchResultObject.results[0].name
//                else
//                    response =searchResultObject.results[0].title
//
//
//            } catch (e: Exception) {
//                response = "Failure: ${e.message}"
//            }
//
//            Log.v("utk",response)
//        }
//
//
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }
//
//}
