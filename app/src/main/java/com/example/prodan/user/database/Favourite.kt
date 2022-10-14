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
data class Favourite @Ignore constructor(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @NonNull @ColumnInfo(name = "name") var name: String,
    @NonNull @ColumnInfo(name = "fav") var fav: Int,
    @NonNull @ColumnInfo(name = "imgUrl") var imgUrl: String
) : Parcelable {

    constructor(name: String, fav: Int, imgUrl: String) : this(0, name, fav, imgUrl){

    }
}