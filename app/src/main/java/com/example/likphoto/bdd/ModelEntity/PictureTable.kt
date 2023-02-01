package com.example.likphoto.bdd.ModelEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "picture_table")
data class PictureTable (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_photos")
    var id: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "alt_description")
    var alt_description: String,

    @ColumnInfo(name = "urls")
    var urls: String,

    @ColumnInfo(name = "likes")
    var likes: Int,

    @ColumnInfo(name = "liked_by_user")
    var liked_by_user: Boolean


)