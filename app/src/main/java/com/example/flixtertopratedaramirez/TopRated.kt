package com.example.flixtertopratedaramirez

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Keep
@Serializable
data class TopRated (
    @SerializedName("release_date")
    var rDate: String?,

    @SerializedName("original_title")
    var title: String?,

    @SerializedName("overview")
    var description: String?,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("backdrop_path")
    var backDrop: String?
    ) : java.io.Serializable

