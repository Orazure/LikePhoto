package com.example.likphoto.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.example.likphoto.models.Urls


@Keep
data class Pictures(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("promoted_at")
    val promotedAt: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("color")
    val color: String,
    @SerializedName("blur_hash")
    val blurHash: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("alt_description")
    val altDescription: String,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    @SerializedName("sponsorship")
    val sponsorship: Any,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    // from urls get raw image
    @SerializedName("urls")
    val urls: Urls
)