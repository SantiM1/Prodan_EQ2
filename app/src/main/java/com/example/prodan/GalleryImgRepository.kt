package com.example.prodan



import androidx.lifecycle.LiveData
import com.example.prodan.user.database.GalleryImg
import com.example.prodan.user.database.ProdanDao

class GalleryImgRepository (private val galleryImgDao: ProdanDao) {

    fun getAllGalleryImgs(): LiveData<List<GalleryImg>> {
        return galleryImgDao.getAllGalleryImgs()
    }


    suspend fun addGalleryImg(galleryImg: GalleryImg){
        galleryImgDao.addGalleryImg(galleryImg)
    }


    suspend fun updateGalleryImg(galleryImg: GalleryImg){
        galleryImgDao.updateGalleryImg(galleryImg)
    }


    suspend fun deleteGalleryImg(galleryImg: GalleryImg){
        galleryImgDao.deleteGalleryImg(galleryImg)
    }


}