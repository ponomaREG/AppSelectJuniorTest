package com.appselect.junior.model

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("type") val type : String,
    @SerializedName("url") val url : String,
    @SerializedName("suggested_link_text") val suggested_link_text : String
)
