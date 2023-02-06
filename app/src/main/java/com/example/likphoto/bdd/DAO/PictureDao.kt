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


        //only if liked_by_user = true
        @Query("SELECT * FROM picture_table WHERE liked_by_user = 1")
        suspend fun getAllData(): List<PictureTable>

        // delete data by id
        @Query("DELETE FROM picture_table WHERE id_photos = :id")
        suspend fun deleteDataById(id: String)

        // update number of likes by id

        // update picture with id
        @Query("UPDATE picture_table SET liked_by_user = :liked WHERE id_photos = :id")
        suspend fun updateDataWithId(id: String, liked: Boolean)



}