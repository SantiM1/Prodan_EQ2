package com.example.prodan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName


@Parcelize
data class pet(
    @SerializedName("pets")
    var pets: List<PetX>
) : Parcelable

@Parcelize
data class PetX(
    @SerializedName("correo")
    val correo: String,
    @SerializedName("custodia")
    var custodia: String,
    @SerializedName("custodiade")
    var custodiade: String,
    @SerializedName("des")
    val des: String,
    @SerializedName("edad")
    val edad: String,
    @SerializedName("es")
    val es: String,
    @SerializedName("fecharescate")
    val fecharescate: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("raza")
    val raza: String,
    @SerializedName("sexo")
    val sexo: String,
    @SerializedName("tamaño")
    val tamaño: String,
    @SerializedName("telefono")
    val telefono: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("fav")
    var fav: Int,
    @SerializedName("click")
    var click: Int
) : Parcelable