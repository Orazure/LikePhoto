package com.example.likphoto.bdd.Model

import com.example.likphoto.API.RepoApi
import com.example.likphoto.bdd.DAO.PictureDao
import com.example.likphoto.bdd.ModelEntity.PictureTable

class PictureRepo(private val service :RepoApi,private val dao: PictureDao) {

    // service et dao
    suspend fun getPicture(): List<PictureTable> {
        return dao.getAllData()
    }

    suspend fun insertPicture(picture: PictureTable) {
        dao.InsertData(picture)
    }

    suspend fun deletePicture(id: String) {
        // if picture exist in database
        if (isPictureExist(id)) {
            dao.deleteDataById(id)
        }
    }

    suspend fun updatePicture(picture: PictureTable) {
        dao.updateData(picture)
    }

    // update picture with id
    suspend fun updatePictureWithId(id: String, liked: Boolean) {
        dao.updateDataWithId(id, liked)
    }

    //isPictureExist
    suspend fun isPictureExist(id: String): Boolean {
        return dao.getPictureDetails(id).isNotEmpty()
    }
    // clear all data
    suspend fun clearAllData() {
        dao.deleteAllData()
    }

    // unlike picture








}