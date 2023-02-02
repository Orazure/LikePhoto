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
        dao.deleteDataById(id)
    }

    suspend fun updatePicture(picture: PictureTable) {
        dao.updateData(picture)
    }

    // unlike picture








}