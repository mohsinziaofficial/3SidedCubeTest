package com.clarity.android.interview.network

import com.mohsin.threesidedcubetest.model.PokemonList
import com.mohsin.threesidedcubetest.model.SinglePokemonItem
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

    // to get response for Pokemon list
    @GET("pokemon/")
    suspend fun fetchPokemonList(): PokemonList

    // to get response for details of individual Pokemon
    @GET("pokemon/{pokemon_id}")
    suspend fun fetchPokemonById(@Path("pokemon_id") pokemonID: Int): SinglePokemonItem
}