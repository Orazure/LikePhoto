package com.example.likphoto.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public object RetrofitApi {
    fun getService(): RepoApi {
        val retrofitBuilder = Retrofit.Builder()

        val okHttpClient = OkHttpClient.Builder()

        retrofitBuilder.client(okHttpClient.build())

        val retrofit = retrofitBuilder
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RepoApi::class.java)

    }

    private const val BASE_URL = "https://api.unsplash.com/"


}