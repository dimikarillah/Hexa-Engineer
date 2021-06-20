package com.hexaengineer.cofinder.core.domain.model

data class Personality(
    val data: String,
    val ext_prediction: Boolean,
    val neu_prediction: Boolean,
    val agr_prediction: Boolean,
    val con_prediction: Boolean,
    val opn_prediction: Boolean,
    val time_consumed: String,
)