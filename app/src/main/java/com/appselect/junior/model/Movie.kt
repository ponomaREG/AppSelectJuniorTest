package com.appselect.junior.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("display_title") val display_title : String,
    @SerializedName("mpaa_rating") val mpaa_rating : String,
    @SerializedName("critics_pick") val critics_pick : Int,
    @SerializedName("byline") val byline : String,
    @SerializedName("headline") val headline : String,
    @SerializedName("summary_short") val summary_short : String,
    @SerializedName("publication_date") val publication_date : String,
    @SerializedName("opening_date") val opening_date : String,
    @SerializedName("date_updated") val date_updated : String,
    @SerializedName("link") val link : Link,
    @SerializedName("multimedia") val multimedia : Multimedia
)
