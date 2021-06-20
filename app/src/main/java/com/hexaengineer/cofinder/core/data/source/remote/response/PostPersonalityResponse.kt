package com.hexaengineer.cofinder.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostPersonalityResponse(
    @field:SerializedName("data")
    val data: String,

    @field:SerializedName("ext_prediction")
    val ext_prediction: Boolean,

    @field:SerializedName("neu_prediction")
    val neu_prediction: Boolean,

    @field:SerializedName("agr_prediction")
    val agr_prediction: Boolean,

    @field:SerializedName("con_prediction")
    val con_prediction: Boolean,

    @field:SerializedName("opn_prediction")
    val opn_prediction: Boolean,

    @field:SerializedName("time_consumed")
    val time_consumed: String
)