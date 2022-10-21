package com.example.prodan.network
import com.google.gson.annotations.SerializedName
import java.util.Date


// POST EMPLOYEE


data class  UsersRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val user: String,
    @SerializedName("tel")
    val tel : String,
    @SerializedName("fecha")
    val date: String,
    @SerializedName("imgl")
    val imgUrl: String
    /*
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
     */
)