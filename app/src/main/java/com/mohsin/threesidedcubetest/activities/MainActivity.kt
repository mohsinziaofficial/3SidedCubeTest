package com.mohsin.threesidedcubetest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clarity.android.interview.adapter.AdapterPokemonList
import com.clarity.android.interview.viewModels.MainActivityViewModel
import com.mohsin.threesidedcubetest.R
import com.mohsin.threesidedcubetest.model.PokemonNameAndImages
import com.mohsin.threesidedcubetest.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterPokemonList
    private lateinit var viewModel: MainActivityViewModel

    private val pokemons = mutableListOf<PokemonNameAndImages>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemScreenContainerView = findViewById<View>(R.id.item_screen_container)
        bindViews(itemScreenContainerView)

        viewModel = MainActivityViewModel()

        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = viewModel.makeApiCall()
            withContext(Dispatchers.Main) {
//                adapter.updateNames(apiResponse as ArrayList<Result>)

                if (apiResponse != null) {
                    for (item in apiResponse) {
                        val first = item.url.toString().substringBeforeLast("/")
                        var pokeURL = first.substringAfterLast("/")

                        Log.d("pokemonsURLs", pokeURL)

                        val pokemonResponse = viewModel.makeIndividualApiCall(pokeURL.toInt())
                        val name = pokemonResponse.name.toString()
                        val img = pokemonResponse.sprites?.backDefault.toString()
                        pokemons.add(PokemonNameAndImages(name, img))
                        adapter.updateImages(pokemons as ArrayList<PokemonNameAndImages>)

            //                    CoroutineScope(Dispatchers.IO).launch {
            //                        val response = viewModel.makeIndividualApiCall(pokeURL.toInt())
            //                        withContext(Dispatchers.Main) {
            //                            pokemonsURLs.add(response.sprites?.backDefault.toString())
            ////                            Log.d("My_Response2", response.sprites?.backDefault.toString())
            //
            //                        }
            //                    }
            //                    Log.d("My_Response2", pokemonsURLs[1])
                    }
                }

                Log.d("My_Response2", pokemons.toString())
            }
        }
    }

    private fun bindViews(parent: View) {
        toolbar = parent.findViewById(R.id.toolbar)
        toolbar.setLogo(R.drawable.logo)
        toolbar.title = "3 Sided Cube Test App"

        recyclerView = parent.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false)

        adapter = AdapterPokemonList()
        recyclerView.adapter = adapter
    }
}