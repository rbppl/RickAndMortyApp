package com.rbppl.rickmortyapp.presentation.locationdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rbppl.rickmortyapp.databinding.ListItemResidentBinding

class ResidentsAdapter(private var residents: List<ResidentInfo>) :
    RecyclerView.Adapter<ResidentsAdapter.ResidentViewHolder>() {

    class ResidentViewHolder(private val binding: ListItemResidentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resident: ResidentInfo) {
            binding.residentName.text = resident.name

            // Загрузка изображения с помощью Glide
            Glide.with(binding.root.context)
                .load(resident.image)
                .into(binding.residentImage)
        }
    }

    fun setData(newResidents: List<ResidentInfo>) {
        residents = newResidents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentViewHolder {
        val binding = ListItemResidentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ResidentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResidentViewHolder, position: Int) {
        holder.bind(residents[position])
    }

    override fun getItemCount(): Int = residents.size
}
