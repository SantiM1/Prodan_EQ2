package com.example.prodan

import android.app.Application
import com.example.prodan.user.database.ProdanDatabase

class ProdanApp : Application() {

    private val databaseGI : ProdanDatabase by lazy { ProdanDatabase.getDatabaseGI(this) }

    val repositoryGallery : GalleryImgRepository by lazy { GalleryImgRepository(databaseGI.galleryImgDao()) }

    private val databaseFv : ProdanDatabase by lazy {ProdanDatabase.getDatabaseFv((this))}

    val repositoryFavourite : FavouriteRepository by lazy {FavouriteRepository(databaseFv.favouriteDao())}

}