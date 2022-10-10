package com.example.prodan.user.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class GalleryImg @Ignore constructor(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @NonNull @ColumnInfo(name = "title") var title: String,
    @NonNull @ColumnInfo(name = "desc") var desc: String,
    @NonNull @ColumnInfo(name = "imgUrl") var imgUrl : String
) : Parcelable {

    constructor(title: String, desc: String, imgUrl: String) : this(0, title, desc, imgUrl){

    }
}