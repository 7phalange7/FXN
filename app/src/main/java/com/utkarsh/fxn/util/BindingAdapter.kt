package com.utkarsh.fxn.util

import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utkarsh.fxn.R
import com.utkarsh.fxn.api.ImdbRatingApiService
import com.utkarsh.fxn.api.ImdbRatingApiService.ImdbRatingApi
import com.utkarsh.fxn.api.SearchApiService
import com.utkarsh.fxn.api.SearchApiService.SearchApi
import com.utkarsh.fxn.data.Result
import com.utkarsh.fxn.ui.searchresult.SearchListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Result>?) {
    val adapter = recyclerView.adapter as SearchListAdapter
    adapter.submitList(data)
}




@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgPath : String?){

    val imgUrl = "https://image.tmdb.org/t/p/w92$imgPath"

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)

    }

}


@BindingAdapter("customName")
fun bindName(txtView: TextView,result: Result){
    if(result.name!=null)
        txtView.text=result.name
    else
        txtView.text=result.title
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("customYear")
fun bindYear(txtView: TextView,result: Result){
    var dates: String
    if(result.firstAirDate!=null)
        dates=result.firstAirDate
    else
        dates=result.releaseDate

    if (dates!=null)
    {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")//default, so formatter need not be passed
        var date = LocalDate.parse(dates, formatter)
        txtView.text= date.year.toString()
    }
    else
        txtView.text="NA"

}


@BindingAdapter("imdbRating")
fun bindRatings(txtView: TextView,result: Result){



    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main +viewModelJob)



    coroutineScope.launch {
        if (result.mediaType=="movie"){
            var getIdDeferred = SearchApi.retrofitService.getImdbIdMovie(result.id)

            try {
                var extIdObject =getIdDeferred.await()
                var getOmdbTitleDeferred = ImdbRatingApi.retrofitService.getOmdbTitle(extIdObject.imdbId)
                try {
                    var omdbTitleObject = getOmdbTitleDeferred.await()
                    txtView.text = omdbTitleObject.imdbRating
                } catch (e : Exception){
                    Log.v("utk","Failure in omdb api: ${e.message}")
                }

            } catch (e : Exception){
                Log.v("utk","Failure in tmdb ext id api: ${e.message}")
            }

        } else {
            var getIdDeferred = SearchApi.retrofitService.getImdbIdTv(result.id)

            try {
                var extIdObject =getIdDeferred.await()
                var getOmdbTitleDeferred = ImdbRatingApi.retrofitService.getOmdbTitle(extIdObject.imdbId)
                try {
                    var omdbTitleObject = getOmdbTitleDeferred.await()
                    txtView.text = omdbTitleObject.imdbRating
                } catch (e : Exception){
                    Log.v("utk","Failure in omdb api: ${e.message}")
                }

            } catch (e : Exception){
                Log.v("utk","Failure: yep !! ${e.message}")
            }

        }


    }





}