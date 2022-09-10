package com.mohsin.threesidedcubetest.model

data class PokemonNameAndImages (
    val name : String,
    val img : String,
    val pokemonStat : List<Stat>?,
)