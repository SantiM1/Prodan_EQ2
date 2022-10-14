package com.example.prodan.user.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavouriteDao {

    @Query("Select * from Favourite")
    fun getAllFavourites():LiveData<List<Favourite>>

    @Query("Delete from Favourite WHERE name=:name")
    fun deleteFavouriteWName(name :String)

    @Query("SELECT COUNT(1) FROM Favourite WHERE name =:name")
    fun getFavouriteWName(name :String): Int
    @Insert
    suspend fun addFavourite(favourite: Favourite)

    @Update
    suspend fun updateFavourite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)
}