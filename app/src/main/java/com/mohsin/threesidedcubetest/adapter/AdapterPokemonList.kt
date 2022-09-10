package com.clarity.android.interview.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohsin.threesidedcubetest.R
import com.mohsin.threesidedcubetest.model.PokemonNameAndImages
import com.mohsin.threesidedcubetest.model.Result
import com.mohsin.threesidedcubetest.model.SinglePokemonItem
import java.util.ArrayList

class AdapterPokemonList  : RecyclerView.Adapter<AdapterPokemonList.ViewHolder>(){

    //    private val orderIDs = ArrayList<Long>()
//    private var result = ArrayList<Result>()
    private var pokemons = ArrayList<PokemonNameAndImages>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.name)
        val img = itemView.findViewById<ImageView>(R.id.img)
        fun bind(pokemon: PokemonNameAndImages) {
            textView.text = pokemon.name
            Glide.with(itemView).load(pokemon.img).into(img)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#808080"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

//    fun updateNames(newItems: ArrayList<Result>) {
//        result = newItems
//        notifyDataSetChanged()
//    }

    fun updateImages(newItems: ArrayList<PokemonNameAndImages>) {
        pokemons = newItems
        notifyDataSetChanged()
    }
}