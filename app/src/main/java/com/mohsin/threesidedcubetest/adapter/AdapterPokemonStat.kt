package com.clarity.android.interview.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohsin.threesidedcubetest.R
import com.mohsin.threesidedcubetest.model.Stat
import java.util.ArrayList

class AdapterPokemonStat : RecyclerView.Adapter<AdapterPokemonStat.ViewHolder>(){

    private var stat = ArrayList<Stat>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statName = itemView.findViewById<TextView>(R.id.statName)
        val baseStat = itemView.findViewById<TextView>(R.id.baseStat)
        val effortText = itemView.findViewById<TextView>(R.id.effort)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)

        // displaying data into views
        fun bind(stat: Stat) {
            statName.text = stat.statX?.name.toString()
            baseStat.text = stat.baseStat.toString()
            effortText.text = stat.effort.toString()

            // animating progress bar horizontally
            progressBar.max = 100
            val currentProgress = stat.baseStat
            if (currentProgress != null) {
                ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
                    .setDuration(2000)
                    .start()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stat_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stat[position])
    }

    override fun getItemCount(): Int {
        return stat.size
    }

    fun update(newItems: ArrayList<Stat>) {
        stat = newItems
        notifyDataSetChanged()
    }
}