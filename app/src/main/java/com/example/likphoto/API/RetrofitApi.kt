package com.example.likphoto.API

import android.media.session.MediaSession.Token
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


public object RetrofitApi {

    //private val TOKEN="69MH3TQiKiIrmww6Ibe1cBJdczgd8hjypwkXyHuMqN0"
    private val TOKEN="9Z6GkYySWcaYoQMFEeiGZGXIu2uaP97g5zwvtqCWoMA"
    fun getService(): RepoApi {
        val retrofitBuilder = Retrofit.Builder()


        val okHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer "+ TOKEN)
                    .build()
                return chain.proceed(newRequest)
            }
        })

        retrofitBuilder.client(okHttpClient.build())



        val retrofit = retrofitBuilder
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        return retrofit.create(RepoApi::class.java)

    }

    private const val BASE_URL = "https://api.unsplash.com/"


}