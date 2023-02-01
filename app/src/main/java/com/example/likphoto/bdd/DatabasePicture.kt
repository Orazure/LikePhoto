package com.example.likphoto.bdd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.likphoto.API.RetrofitViewModel
import com.example.likphoto.bdd.DAO.PictureDao
import com.example.likphoto.bdd.ModelEntity.PictureTable

@Database(entities = [PictureTable::class], version =2)
abstract class DatabasePicture :RoomDatabase() {
    abstract fun getPictureDao(): PictureDao

    companion object {
        const val DATABASE_NAME = "picture-database"

        @Volatile
        private var INSTANCE: DatabasePicture? = null

        fun getInstance(context: Context): DatabasePicture {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, DatabasePicture::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}