package com.example.prodan



import androidx.lifecycle.LiveData
import com.example.prodan.user.database.Favourite
import com.example.prodan.user.database.FavouriteDao


class FavouriteRepository (private val favouriteDao : FavouriteDao) {

    fun getAllFavourites(): LiveData<List<Favourite>> {
        return favouriteDao.getAllFavourites()
    }


    suspend fun addFavourite(favourite: Favourite){
        favouriteDao.addFavourite(favourite)
    }


    suspend fun updateFavourite(favourite: Favourite){
        favouriteDao.updateFavourite(favourite)
    }


    suspend fun deleteFavourite(favourite: Favourite){
        favouriteDao.deleteFavourite(favourite)
    }


}