package com.appselect.junior.model

import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("has_more") val has_more : Boolean,
    @SerializedName("num_results") val num_results : Int,
    @SerializedName("results") val results : List<Movie>
)