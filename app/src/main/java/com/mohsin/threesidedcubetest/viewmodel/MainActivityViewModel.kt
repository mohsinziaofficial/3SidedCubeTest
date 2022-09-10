package com.clarity.android.interview.viewModels

import androidx.lifecycle.ViewModel
import com.mohsin.threesidedcubetest.model.Result
import com.mohsin.threesidedcubetest.model.SinglePokemonItem
import com.mohsin.threesidedcubetest.network.NetworkService

class MainActivityViewModel: ViewModel() {

  val retroInstance = NetworkService().api

  // api call for all Pokemon list
  suspend fun makeApiCall() : List<Result>? {
    return retroInstance.fetchPokemonList().results
  }

  // api for details about individual Pokemon
  suspend fun makeIndividualApiCall(pokemonID : Int) : SinglePokemonItem {
    return retroInstance.fetchPokemonById(pokemonID)
  }
}
