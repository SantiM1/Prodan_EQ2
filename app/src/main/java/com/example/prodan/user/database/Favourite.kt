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
    @PrimaryKey (autoGenerate = true) var id:Int,
    @NonNull @ColumnInfo(name = "name") var name: String,
    @NonNull @ColumnInfo(name = "fav") var fav: Int,
    @NonNull @ColumnInfo(name = "imgUrl") var imgUrl: String,
    @NonNull @ColumnInfo(name = "raza") var raza: String,
    @NonNull @ColumnInfo(name = "edad") var edad: String,
    @NonNull @ColumnInfo(name = "size") var size: String,
    @NonNull @ColumnInfo(name = "custodia") var custodia: String,
    @NonNull @ColumnInfo(name = "pid") var pid: Int,

) : Parcelable {

    constructor(
        name: String,
        fav: Int,
        imgUrl: String,
        raza: String,
        edad: String,
        size: String,
        custodia: String,
        pid: Int
    ) : this(0, name, fav, imgUrl, raza, edad, size, custodia, pid){

    }
}