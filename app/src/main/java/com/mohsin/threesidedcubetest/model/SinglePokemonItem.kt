package com.mohsin.threesidedcubetest.model


import com.google.gson.annotations.SerializedName

data class SinglePokemonItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val sprites: Sprites?,
    @SerializedName("stats")
    val stats: List<Stat>?,
    @SerializedName("weight")
    val weight: Int?
)