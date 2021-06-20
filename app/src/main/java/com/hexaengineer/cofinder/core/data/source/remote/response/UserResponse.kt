package com.hexaengineer.cofinder.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("data")
    val user: List<UserItem>

)

data class UserItem(

    @field:SerializedName("ID_USER")
    val id: Int,

    @field:SerializedName("NAME")
    val name: String,

    @field:SerializedName("SKILLS")
    val skills: String,

    @field:SerializedName("PICTURE")
    val picture: String,

    @field:SerializedName("CITY")
    val city: String
)
