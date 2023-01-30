package com.example.likphoto.API

import android.content.Context
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import okhttp3.Response
import com.example.likphoto.API.RepoApi
import android.util.Log
import androidx.lifecycle.*
import com.example.likphoto.bdd.DAO.PictureDao
import com.example.likphoto.bdd.DatabasePicture
import com.example.likphoto.bdd.ModelEntity.PictureTable
import com.example.likphoto.models.Pictures
import com.google.gson.Gson
import okhttp3.Interceptor

// import API from local.properties


class RetrofitViewModel(private val databasePicture: DatabasePicture) : ViewModel() {


    private val _data=MutableLiveData<List<Pictures>>()
    val data:LiveData<List<Pictures>> =_data

    private val Api= RetrofitApi.getService()


    fun getdata(){
        viewModelScope.launch {
            // response of list of pictures from unsplash
            val response=Api.getData()
            // get all pictures in list
            _data.value=response
        }
    }

    fun getdataApiToDB(id : String){
        viewModelScope.launch {
            val likePicture=Api.likePicture(id)
            val toInsert=PictureTable(id,likePicture.description,likePicture.urls.regular,likePicture.likes,likePicture.likedByUser)
            databasePicture.getPictureDao().InsertData(toInsert)
        }
    }

    // function to like picture or unlike picture
    fun likePicture(id:String){
        viewModelScope.launch {
            // like picture
            val likedPicture = Api.likePicture(id)
            getdataApiToDB(id)
            _data.value = listOf(likedPicture)
        }
    }

    // function to unlike picture
    fun unlikePicture(id:String){

        viewModelScope.launch {
            // response of list of pictures from unsplash
            listOf(Api.unlikePicture(id))
        }
    }

    // function to get liked pictures
    fun getLikedPictures(){
        viewModelScope.launch {
            // response of list of pictures from unsplash
            _data.value= Api.getLikedPictures()
        }
    }



}

class UserViewModelFactory(val databasePicture: DatabasePicture) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(databasePicture) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
