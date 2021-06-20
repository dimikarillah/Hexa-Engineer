package com.hexaengineer.cofinder.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail")
data class UserDetailEntity(
    @PrimaryKey
    val id: Int? = 0,
    val personalities: String? = null,
    val description: String? = null,
    val address: String? = null,
    val kontak: String? = null
)