package com.hexaengineer.cofinder.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("status")
    val status: Int
)

data class DataItem(

    @field:SerializedName("ID_USER")
    val id: Int,

    @field:SerializedName("PERSONALITIES")
    val personalities: String,

    @field:SerializedName("DESCRIPTION")
    val description: String,

    @field:SerializedName("ADDRESS")
    val address: String,

    @field:SerializedName("KONTAK")
    val kontak: String
)
