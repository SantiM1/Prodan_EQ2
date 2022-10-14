package com.example.prodan.user.database


import  android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GalleryImg::class, Favourite::class],version=1)
abstract class ProdanDatabase : RoomDatabase() {

    abstract  fun galleryImgDao(): ProdanDao
    abstract  fun favouriteDao(): FavouriteDao

    companion object {

        @Volatile
            private var INSTANCE: ProdanDatabase? = null

        fun getDatabaseGI(context: Context) : ProdanDatabase {

            return  INSTANCE ?: synchronized(this) {

                val instance = Room
                    .databaseBuilder(context, ProdanDatabase::class.java, "prodan_db")
                    .build()
                INSTANCE

                instance
            }
        }
        fun getDatabaseFv(context: Context) : ProdanDatabase {

            return  INSTANCE ?: synchronized(this) {

                val instance = Room
                    .databaseBuilder(context, ProdanDatabase::class.java, "prodan_db").allowMainThreadQueries()
                    .build()
                INSTANCE

                instance
            }
        }
    }


}