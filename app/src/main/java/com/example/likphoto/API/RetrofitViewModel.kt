package com.example.likphoto.API

import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import okhttp3.Response
import com.example.likphoto.API.RepoApi
import android.util.Log
import androidx.lifecycle.*
import com.example.likphoto.models.Pictures
// import API from local.properties


class RetrofitViewModel() : ViewModel() {

    private val API="_FtbZ8UWpg7ZiezxsmO4LgOclxrba18mlVo8skccotQ"
    private val _data=MutableLiveData<List<Pictures>>()
    val data:LiveData<List<Pictures>> =_data

    fun getdata(){

        val Api= RetrofitApi.getService()
        viewModelScope.launch {
            // response of list of pictures from unsplash
            val response=Api.getData()
            // get all pictures in list
            _data.value=response
        }
    }

    // function to like picture or unlike picture
    fun likePicture(id:String){
        val Api= RetrofitApi.getService()
        viewModelScope.launch {
            // like picture
            _data.value= listOf(Api.likePicture(id))
        }
    }

    // function to unlike picture
    fun unlikePicture(id:String){
        val Api= RetrofitApi.getService()
        viewModelScope.launch {
            // response of list of pictures from unsplash
            listOf(Api.unlikePicture(id))
        }
    }

    // function to get liked pictures
    fun getLikedPictures(){
        val Api= RetrofitApi.getService()
        viewModelScope.launch {
            // response of list of pictures from unsplash
            _data.value= Api.getLikedPictures()
        }
    }


}

class UserViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
