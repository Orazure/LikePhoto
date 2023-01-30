package com.example.likphoto.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Urls (
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String,
)