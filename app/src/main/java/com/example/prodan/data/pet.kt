package com.example.prodan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class pet (
    val nombre: String,
    val imagen: Int
) : Parcelable