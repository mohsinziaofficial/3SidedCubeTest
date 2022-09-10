package com.mohsin.threesidedcubetest.model


import com.google.gson.annotations.SerializedName

data class SinglePokemonItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("past_types")
    val pastTypes: List<String>?,
    @SerializedName("species")
    val species: Species?,
    @SerializedName("sprites")
    val sprites: Sprites?,
    @SerializedName("stats")
    val stats: List<Stat>?,
    @SerializedName("weight")
    val weight: Int?
)