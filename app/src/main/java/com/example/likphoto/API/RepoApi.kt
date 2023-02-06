package com.example.likphoto.API

import com.example.likphoto.models.IndexSearch
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
    @GET("users/wizz_/likes")
    suspend fun getLikedPictures(): List<Pictures>

    // get photo by id
    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") id: String): Pictures

    // search by index of photo with 1 page , the picture is in results
    @GET("search/photos")
    suspend fun searchPhoto(@Query("query") query: String, @Query("page") page: Int):IndexSearch






}