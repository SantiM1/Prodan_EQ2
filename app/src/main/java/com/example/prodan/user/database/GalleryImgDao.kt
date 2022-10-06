package com.example.prodan.user.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GalleryImgDao {

    @Query("Select * from GALLERYIMG order by id desc")
    fun getAllGalleryImgs():LiveData<List<GalleryImg>>

    @Insert
    suspend fun addGalleryImg(galleryImg: GalleryImg)

    @Update
    suspend fun updateGalleryImg(galleryImg: GalleryImg)

    @Delete
    suspend fun deleteGalleryImg(galleryImg: GalleryImg)
}