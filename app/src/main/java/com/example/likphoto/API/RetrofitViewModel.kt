package com.example.likphoto.API

import androidx.lifecycle.*
import com.example.likphoto.bdd.Model.PictureRepo
import com.example.likphoto.bdd.ModelEntity.PictureTable
import com.example.likphoto.models.Pictures
import com.example.likphoto.models.Urls
import kotlinx.coroutines.launch

// import API from local.properties


class RetrofitViewModel(private val repo:PictureRepo) : ViewModel() {


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

    fun getLikedPicturesAct(){
        viewModelScope.launch {
            val data=repo.getPicture() // get all pictures liked from database
            if(data.isEmpty()){
                val response=Api.getLikedPictures()
                // get all pictures in list
                _data.value=response
            }else{
                _data.value=data.map {
                    Pictures(it.id,"","","",0,0,"","",it.description,it.alt_description, listOf(),it.likes,it.liked_by_user,
                        listOf(),"",0,0, Urls("","",it.urls,"",""))
                }
            }
        }
    }
    // func to search by index of photo with 1 page
    fun searchPhotoAct(query:String, page:Int){
        println("query $query")
        viewModelScope.launch {
            println("dans le launch")
            val response=Api.searchPhoto(query,page)
            println(response)
            _data.value=response.results
            //{
            //    "total": 10009,
            //    "total_pages": 1001,
            //    "results": [
            //        {
            //  the result is like this and we get only results
            // get all pictures in list
            //response.forEach {
              //  println(response)
                //_data.value=response
            //}
        }
    }




    // function to like picture or unlike picture
    fun likePicture(id:String){
        viewModelScope.launch {
            // like picture
            // if i not like before
            if(!repo.isPictureExist(id)) {
                val likedPicture = Api.likePicture(id)
                val getInfoPicture = Api.getPhotoById(id)
                val noDesc =
                    if (getInfoPicture.description == null) "" else getInfoPicture.description
                val noAltDesc =
                    if (getInfoPicture.altDescription == null) "" else getInfoPicture.altDescription
                val picture = PictureTable(
                    getInfoPicture.id,
                    noDesc,
                    noAltDesc,
                    getInfoPicture.urls.regular,
                    getInfoPicture.likes,
                    getInfoPicture.likedByUser
                )
                repo.insertPicture(picture)
                _data.value = listOf(likedPicture)
            }else{
                // send is already liked
                val likedPicture = Api.likePicture(id)
                _data.value = listOf(likedPicture)
            }
        }
    }

    // function to unlike picture
    fun unlikePicture(id:String){
        viewModelScope.launch {
            // response of list of pictures from unsplash
            Api.unlikePicture(id)
            repo.deletePicture(id)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            println("delete all")
            // delete all pictures from database and after call thread sleep
            repo.clearAllData()
        }
    }
    // function to get liked pictures
    fun getLikedPictures(){
        viewModelScope.launch {
            // response of list of pictures from unsplash
            val responseFromDb=repo.getPicture()
            val response=Api.getLikedPictures()
            if(responseFromDb.isEmpty()){
                println("is empty db")
                response.forEach {
                    val notDesc=it.description ?: "pas de description"
                    val notAltDesc=it.altDescription ?: "pas de description"
                    repo.insertPicture(PictureTable(it.id,notDesc,notAltDesc,it.urls.regular,it.likes,it.likedByUser))
                }
                _data.value=response.map {
                    Pictures(it.id,"","","",0,0,"","",it.description ?: "",it.altDescription ?:"", listOf(),it.likes,it.likedByUser,
                        listOf(),"",0,0, Urls("","",it.urls.regular,"",""))
                }
            }else{
                println("is not empty db")
                // insert pictures if not exist in database
                responseFromDb.forEach {
                    response.forEach { picture ->
                        // print the picture
                        // if the picture match with the picture in database
                        // set the liked by user to true
                        if(!it.liked_by_user){
                            repo.deletePicture(it.id)
                        }
                        if(picture.id==it.id){
                            repo.updatePictureWithId(picture.id,true)
                        }
                        if(picture.id!=it.id){
                            val notDesc=picture.description ?: "pas de description"
                            val notAltDesc=picture.altDescription ?: "pas de description"
                            repo.insertPicture(PictureTable(picture.id,notDesc,notAltDesc,picture.urls.regular,picture.likes,picture.likedByUser))
                        }
                    }
                }
                _data.value=responseFromDb.map {
                    Pictures(it.id,"","","",0,0,"","",it.description,it.alt_description, listOf(),it.likes,it.liked_by_user,
                        listOf(),"",0,0, Urls("","",it.urls,"",""))
                }
            }
        }
    }



}

class UserViewModelFactory(val repo: PictureRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
