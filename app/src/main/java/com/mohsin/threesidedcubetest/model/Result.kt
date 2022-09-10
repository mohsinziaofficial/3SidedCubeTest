package com.mohsin.threesidedcubetest.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("url")
    val url: String?
)