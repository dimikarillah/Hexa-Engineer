package com.hexaengineer.cofinder.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val name: String,
    val skills: String,
    val picture: String,
    val city: String
)