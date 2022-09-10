package com.mohsin.threesidedcubetest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clarity.android.interview.adapter.AdapterPokemonStat
import com.clarity.android.interview.viewModels.MainActivityViewModel
import com.mohsin.threesidedcubetest.R
import com.mohsin.threesidedcubetest.model.Stat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonStats : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterPokemonStat
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_stats)

        val itemScreenContainerView = findViewById<View>(R.id.item_screen_container)
        bindViews(itemScreenContainerView)

        // initializing viewModel
        viewModel = MainActivityViewModel()
        img = findViewById(R.id.img)

        val pokemonID = intent.getIntExtra("pokemonID", 1)

        // calling api for individual Pokemon details e.g., stat, effort, and sprites
        CoroutineScope(Dispatchers.IO).launch {

            // storing api response for further use
            val pokemonResponse = viewModel.makeIndividualApiCall(pokemonID)

            withContext(Dispatchers.Main) {

                // displaying sprite in imageview
                Glide.with(this@PokemonStats).load(pokemonResponse.sprites?.frontDefault.toString()).into(img)

                // populating data in the recycler view
                adapter.update(pokemonResponse.stats as ArrayList<Stat>)
                toolbar.title = pokemonResponse.name
            }
        }
    }

    // initializing views
    private fun bindViews(parent: View) {
        toolbar = parent.findViewById(R.id.toolbar)
        toolbar.setLogo(R.drawable.logo)

        recyclerView = parent.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false)

        adapter = AdapterPokemonStat()
        recyclerView.adapter = adapter
    }
}