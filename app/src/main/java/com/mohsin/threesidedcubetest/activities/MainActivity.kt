package com.mohsin.threesidedcubetest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clarity.android.interview.adapter.AdapterPokemonList
import com.clarity.android.interview.viewModels.MainActivityViewModel
import com.mohsin.threesidedcubetest.R
import com.mohsin.threesidedcubetest.model.PokemonNameAndImages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        // initializing viewModel
        viewModel = MainActivityViewModel()

        // calling base api to fetch the list of Pokemons
        CoroutineScope(Dispatchers.IO).launch {

            // storing api response for further use
            val apiResponse = viewModel.makeApiCall()

            withContext(Dispatchers.Main) {

                // getting URLs from api response
                if (apiResponse != null) {

                    // we have to separate the Pokemon Index from Url so we can
                    // use it as a api parameter to get details about individual Pokemon
                    for (item in apiResponse) {

                        // https://pokeapi.co/api/v2/pokemon/1/ from this url first we separated
                        // ending '/' in 1st step then took out the number i.e., '1' in 2nd step
                        val urlFirstHalf = item.url.toString().substringBeforeLast("/")
                        var pokemonIndex = urlFirstHalf.substringAfterLast("/")

                        // calling api for individual Pokemon details e.g., sprites
                        val pokemonResponse = viewModel.makeIndividualApiCall(pokemonIndex.toInt())
                        val name = pokemonResponse.name.toString()
                        val img = pokemonResponse.sprites?.frontDefault.toString()
                        val stat = pokemonResponse.stats

                        // we created a custom model class to mould the data according to our
                        // own requirement because Sprites are included in individual link details
                        // and we displayed it in all Pokemon list
                        pokemons.add(PokemonNameAndImages(name, img, stat))

                        // populating data in the recycler view
                        adapter.update(pokemons as ArrayList<PokemonNameAndImages>)
                    }
                }
            }
        }
    }

    // initializing views
    private fun bindViews(parent: View) {
        toolbar = parent.findViewById(R.id.toolbar)
        toolbar.setLogo(R.drawable.logo)
        toolbar.title = "3 Sided Cube Test App"

        recyclerView = parent.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false)

        // triggering click event
        adapter = AdapterPokemonList(){
                selectedItem : Int -> itemClicked(selectedItem)
        }
        recyclerView.adapter = adapter
    }

    // click event
    fun itemClicked(position : Int) {
        val intent = Intent(this, PokemonStats::class.java)
        intent.putExtra("pokemonID", position)
        startActivity(intent)
    }
}