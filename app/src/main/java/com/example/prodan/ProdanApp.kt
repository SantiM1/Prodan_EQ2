package com.example.prodan

import android.app.Application
import com.example.prodan.user.database.ProdanDatabase

class ProdanApp : Application() {

    val database : ProdanDatabase by lazy { ProdanDatabase.getDatabase(this) }

    val repositoryGallery : GalleryImgRepository by lazy { GalleryImgRepository(database.galleryImgDao()) }

}