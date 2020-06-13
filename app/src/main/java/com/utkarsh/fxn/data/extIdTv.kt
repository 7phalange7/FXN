package com.utkarsh.fxn.data


import com.google.gson.annotations.SerializedName

data class extIdTv(
    @SerializedName("facebook_id")
    val facebookId: Any,
    @SerializedName("freebase_id")
    val freebaseId: Any,
    @SerializedName("freebase_mid")
    val freebaseMid: Any,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("instagram_id")
    val instagramId: Any,
    @SerializedName("tvdb_id")
    val tvdbId: Int,
    @SerializedName("tvrage_id")
    val tvrageId: Any,
    @SerializedName("twitter_id")
    val twitterId: Any
)