package com.clarity.android.interview.viewModels

import androidx.lifecycle.ViewModel
import com.mohsin.threesidedcubetest.model.Result
import com.mohsin.threesidedcubetest.model.SinglePokemonItem
import com.mohsin.threesidedcubetest.model.Sprites
import com.mohsin.threesidedcubetest.network.NetworkService

class MainActivityViewModel: ViewModel() {

  val retroInstance = NetworkService().api

  suspend fun makeApiCall() : List<Result>? {
    return retroInstance.fetchPokemonList().results
  }

  suspend fun makeIndividualApiCall(pokemonID : Int) : SinglePokemonItem {
    return retroInstance.fetchPokemonById(pokemonID)
  }
}
