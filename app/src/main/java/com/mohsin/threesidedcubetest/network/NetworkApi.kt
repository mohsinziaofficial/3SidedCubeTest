package com.clarity.android.interview.network

import com.mohsin.threesidedcubetest.model.PokemonList
import com.mohsin.threesidedcubetest.model.SinglePokemonItem
import com.mohsin.threesidedcubetest.model.Sprites
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

    @GET("pokemon/")
    suspend fun fetchPokemonList(): PokemonList

    @GET("pokemon/{pokemon_id}")
    suspend fun fetchPokemonById(@Path("pokemon_id") pokemonID: Int): SinglePokemonItem
}