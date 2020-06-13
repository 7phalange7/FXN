package com.utkarsh.fxn.data


import com.google.gson.annotations.SerializedName

data class extIdMovie(
    @SerializedName("facebook_id")
    val facebookId: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("instagram_id")
    val instagramId: Any,
    @SerializedName("twitter_id")
    val twitterId: Any
)