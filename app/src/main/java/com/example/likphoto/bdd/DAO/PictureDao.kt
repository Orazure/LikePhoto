package com.example.likphoto.bdd.DAO

import androidx.room.*
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

        // update unlike by id
        @Query("UPDATE picture_table SET liked_by_user = 'false' WHERE id_photos = :id")
        suspend fun updateUnlikeById(id: String)


}