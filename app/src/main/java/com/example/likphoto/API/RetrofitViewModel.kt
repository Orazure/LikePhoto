package com.example.likphoto.API

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import okhttp3.Response
import com.example.likphoto.API.RepoApi
import android.util.Log
import com.example.likphoto.models.Pictures

class RetrofitViewModel(private val repository: RepoApi) : ViewModel() {

    private val API="_FtbZ8UWpg7ZiezxsmO4LgOclxrba18mlVo8skccotQ"
    private val _data=MutableLiveData<List<Pictures>>()
    val data:LiveData<List<Pictures>> =_data

    fun getdata(){

        val Api= RetrofitApi.getService()
        viewModelScope.launch {
            // response of list of pictures from unsplash
            val response=Api.getData(API)
            // get all pictures in list
            _data.value=response
        }
    }


}
