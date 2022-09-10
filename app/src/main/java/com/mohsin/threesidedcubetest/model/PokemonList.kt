package com.mohsin.threesidedcubetest.model


import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("results")
    val results: List<Result>?
)