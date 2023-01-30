package com.example.likphoto.API

import android.telecom.Call
import com.example.likphoto.API.RetrofitApi
import com.example.likphoto.models.Pictures
import retrofit2.http.*

public interface RepoApi {

    // header authorization baerer token

    // get pictures from unsplash with API key
    @GET("photos")
    suspend fun getData(): List<Pictures>

    // get pictures from unsplash with API key
    @GET("photos/{id}")
    suspend fun getPosts(@Path("id") id: String): List<Pictures>


    // like picture and return nothing
    @POST("photos/{id}/like")
    suspend fun likePicture(@Path("id") id: String) : Pictures

    // unlike picture
    @DELETE("photos/{id}/like")
    suspend fun unlikePicture(@Path("id") id: String) : Pictures

    // get liked pictures
    @GET("users/alex_wizz/likes")
    suspend fun getLikedPictures(): List<Pictures>







}