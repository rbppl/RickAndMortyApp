package com.rbppl.rickmortyapp.presentation.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rbppl.rickmortyapp.R
import com.rbppl.rickmortyapp.data.remote.EpisodeResponse

class EpisodesAdapter(private val episodes: MutableList<EpisodeResponse>) :
    RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val episodeNameTextView: TextView = itemView.findViewById(R.id.episodeNameTextView)
        val episodeAirDateTextView: TextView = itemView.findViewById(R.id.episodeAirDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        holder.episodeNameTextView.text = episode.name
        holder.episodeAirDateTextView.text = episode.airDate
    }
    fun updateEpisodes(newEpisodes: List<EpisodeResponse>) {
        episodes.clear()
        episodes.addAll(newEpisodes)
        notifyDataSetChanged()
    }
    fun clearEpisodes() {
        episodes.clear()
        notifyDataSetChanged()
    }

    fun addAllEpisodes(newEpisodes: List<EpisodeResponse>) {
        episodes.addAll(newEpisodes)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = episodes.size
}
