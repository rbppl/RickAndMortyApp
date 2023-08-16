package com.rbppl.rickmortyapp.presentation.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rbppl.rickmortyapp.databinding.ListItemLocationBinding
import com.rbppl.rickmortyapp.domain.model.Location

class LocationsAdapter(private var locations: List<Location>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(location: Location)
    }

    var locationsList: List<Location>
        get() = locations
        set(value) {
            locations = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ListItemLocationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    override fun getItemCount(): Int = locations.size

    inner class LocationViewHolder(private val binding: ListItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(locations[position])
                }
            }
        }

        fun bind(location: Location) {
            binding.locationName.text = location.name
        }
    }
}
