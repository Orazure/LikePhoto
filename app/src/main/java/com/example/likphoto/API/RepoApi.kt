package com.example.likphoto.API

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.likphoto.API.RetrofitApi
import com.example.likphoto.models.Pictures

public interface RepoApi {



    // get pictures from unsplash with API key
    @GET("photos")
    suspend fun getData(@Query("client_id") client_id: String?): List<Pictures>

    // get pictures from unsplash with API key
    @GET("photos/{id}")
    suspend fun getPosts(@Path("id") id: String?, @Query("client_id") client_id: String?): List<Pictures>







}