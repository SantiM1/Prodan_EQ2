package com.example.prodan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.prodan.user.database.Favourite
import com.example.prodan.user.database.GalleryImg
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: FavouriteRepository) : ViewModel(){
    fun getAllFavourites(): LiveData<List<Favourite>> {
        return repository.getAllFavourites()
    }

    fun deleteFavouriteWName(name:String){
        return repository.deleteFavouritesWN(name)
    }

    fun getFavouriteWName(name:String): Int{
        return repository.getFavouritesWN(name)
    }



    fun addFavourite(favourite: Favourite){
        viewModelScope.launch { repository.addFavourite(favourite) }

    }


    fun updateFavourite(favourite: Favourite){
        viewModelScope.launch { repository.updateFavourite(favourite) }
    }


    fun deleteFavourite(favourite: Favourite){
        viewModelScope.launch { repository.deleteFavourite(favourite) }
    }
}

class FavouriteViewModelFactory (private val repository: FavouriteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}