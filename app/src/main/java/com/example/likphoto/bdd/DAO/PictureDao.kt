package com.example.likphoto.bdd.DAO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.likphoto.bdd.ModelEntity.PictureTable

@Dao
interface PictureDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun InsertData(picture: PictureTable)

        @Query("SELECT * FROM picture_table WHERE id_photos = :id")
        suspend fun getPictureDetails(id: String?) : List<PictureTable>

        @Update
        suspend fun updateData(picture: PictureTable)

        @Delete
        suspend fun deleteData(picture: PictureTable)

        @Query("DELETE FROM picture_table")
        suspend fun deleteAllData()

        @Query("SELECT * FROM picture_table")
        suspend fun getAllData(): List<PictureTable>

        // delete data by id
        @Query("DELETE FROM picture_table WHERE id_photos = :id")
        suspend fun deleteDataById(id: String)

        // update number of likes by id
        @Query("UPDATE picture_table SET likes = :likes WHERE id_photos = :id")
        suspend fun updateLikesById(id: String, likes: Int)

        // update unlike by id
        @Query("UPDATE picture_table SET liked_by_user = :liked_by_user WHERE id_photos = :id")
        suspend fun updateUnlikeById(id: String, liked_by_user: Boolean)


}