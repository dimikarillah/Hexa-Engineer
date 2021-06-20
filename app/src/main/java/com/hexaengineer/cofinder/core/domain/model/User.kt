package com.hexaengineer.cofinder.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val skills: String,
    val picture: String,
    val city: String
) : Parcelable