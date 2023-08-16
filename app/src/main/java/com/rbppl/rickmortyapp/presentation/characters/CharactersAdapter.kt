package com.rbppl.rickmortyapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rbppl.rickmortyapp.databinding.ListItemCharacterBinding
import com.rbppl.rickmortyapp.domain.model.Character
class CharactersAdapter(private var characters: List<Character>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private var onItemClick: ((Character) -> Unit)? = null

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClick = listener
    }

    var charactersList: List<Character>
        get() = characters
        set(value) {
            characters = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    inner class CharacterViewHolder(private val binding: ListItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.characterName.text = character.name
            Glide.with(itemView.context)
                .load(character.image)
                .into(binding.characterImage)

            itemView.setOnClickListener {
                onItemClick?.invoke(character)
            }
        }
    }
}
