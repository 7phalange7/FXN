package com.utkarsh.fxn.data.omdb


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("Source")
    var source: String? = null,
    @SerializedName("Value")
    var value: String? = null
)