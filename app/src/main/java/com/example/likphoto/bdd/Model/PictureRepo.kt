package com.example.likphoto.bdd.Model

import android.content.Context
import androidx.lifecycle.*
import com.example.likphoto.API.RepoApi
import com.example.likphoto.API.RetrofitApi
import com.example.likphoto.bdd.DAO.PictureDao
import com.example.likphoto.bdd.DatabasePicture
import com.example.likphoto.bdd.ModelEntity.PictureTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PictureRepo(private val service :RepoApi,private val dao: PictureDao) {

    // service et dao
    suspend fun getPicture(): List<PictureTable> {
        return dao.getAllData()
    }

    suspend fun insertPicture(picture: PictureTable) {
        dao.InsertData(picture)
    }

    suspend fun deletePicture(id: String) {
        dao.deleteDataById(id)
    }

    suspend fun updatePicture(picture: PictureTable) {
        dao.updateData(picture)
    }

    // unlike picture








}