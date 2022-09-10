package com.clarity.android.interview.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohsin.threesidedcubetest.R
import com.mohsin.threesidedcubetest.model.PokemonNameAndImages
import com.mohsin.threesidedcubetest.model.Result
import com.mohsin.threesidedcubetest.model.SinglePokemonItem
import java.util.ArrayList

class AdapterPokemonList (private val clickListener : (Int)->Unit) : RecyclerView.Adapter<AdapterPokemonList.ViewHolder>(){

    private var pokemons = ArrayList<PokemonNameAndImages>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.name)
        val img = itemView.findViewById<ImageView>(R.id.img)
        fun bind(pokemon: PokemonNameAndImages, position: Int, clickListener: (Int) -> Unit) {
            textView.text = pokemon.name
            Glide.with(itemView).load(pokemon.img).into(img)

            itemView.setOnClickListener {
                Log.d("My_Position:", position.toString())
                clickListener(position+1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemons[position], position, clickListener)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    fun update(newItems: ArrayList<PokemonNameAndImages>) {
        pokemons = newItems
        notifyDataSetChanged()
    }
}