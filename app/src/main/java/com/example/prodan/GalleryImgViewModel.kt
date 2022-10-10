package com.example.prodan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.prodan.user.database.GalleryImg
import kotlinx.coroutines.launch

class GalleryImgViewModel(private val repository: GalleryImgRepository) : ViewModel(){
    fun getAllGalleryImgs(): LiveData<List<GalleryImg>> {
        return repository.getAllGalleryImgs()
    }


    fun addGalleryImg(galleryImg: GalleryImg){
        viewModelScope.launch { repository.addGalleryImg(galleryImg) }

    }


    fun updateGalleryImg(galleryImg: GalleryImg){
        viewModelScope.launch { repository.updateGalleryImg(galleryImg) }
    }


    fun deleteGalleryImg(galleryImg: GalleryImg){
        viewModelScope.launch { repository.deleteGalleryImg(galleryImg) }
    }
}

class GalleryImgViewModelFactory (private val repository: GalleryImgRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryImgViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryImgViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}