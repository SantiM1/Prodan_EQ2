package com.example.prodan.user.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GalleryImg::class], version = 1)
abstract class GalleryImgDatabase: RoomDatabase() {

    abstract fun galleryImgDao() : GalleryImgDao

    companion object{

        @Volatile
        private var INSTANCE: GalleryImgDatabase? = null

        fun getDatabase(context: Context) : GalleryImgDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room
                    .databaseBuilder(context, GalleryImgDatabase::class.java, "galleryimg_db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}