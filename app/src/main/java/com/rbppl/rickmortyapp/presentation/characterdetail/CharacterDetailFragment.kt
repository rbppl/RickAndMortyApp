package com.rbppl.rickmortyapp.presentation.characterdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService
import com.rbppl.rickmortyapp.databinding.FragmentCharacterDetailBinding
import com.rbppl.rickmortyapp.domain.model.Character
import com.rbppl.rickmortyapp.data.remote.EpisodeResponse
import com.rbppl.rickmortyapp.data.repository.EpisodeRepository
import com.rbppl.rickmortyapp.presentation.episodes.EpisodesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val apiService = RickAndMortyApiService.api
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/") // Базовый URL API
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val episodeRepository = EpisodeRepository(apiService)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = arguments?.getSerializable("character") as Character
        binding.backButton1.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.characterDetailName.text = character.name
        binding.characterDetailGender.text = character.gender
        binding.characterDetailSpecies.text = character.species
        binding.characterDetailOrigin.text = character.origin.name
        Log.d("CharacterDetailFragment", "[[Character Name]]: ${character}")

        val episodesRecyclerView = binding.episodesRecyclerView
        val episodesAdapter = EpisodesAdapter(mutableListOf())
        episodesRecyclerView.adapter = episodesAdapter

        if (character.episodes != null) {

            val firstFiveEpisodes = character.episodes.take(5)

            val episodesList = mutableListOf<EpisodeResponse>()

            firstFiveEpisodes.forEach { episodeUrl ->
                Log.d("CharacterDetailFragment", "Fetching episode details for URL: $episodeUrl")
                getEpisodeDetails(episodeUrl) { episodeResponse ->
                    Log.d("CharacterDetailFragment", "Received episode details: $episodeResponse")
                    episodesList.add(episodeResponse)
                    if (episodesList.size == firstFiveEpisodes.size) {
                        Log.d("CharacterDetailFragment", "All episodes processed. Updating adapter.")
                        episodesAdapter.updateEpisodes(episodesList)
                        episodesRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }


        // Добавьте другие поля для детальной информации
        Glide.with(requireContext())
            .load(character.image)
            .into(binding.characterDetailImage)
    }

    private fun getEpisodeIdFromUrl(url: String): Int {
        val parts = url.split("/")
        return parts.last().toInt()
    }

    private fun getAirDateForEpisode(url: String, callback: (String) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val episodeResponse = episodeRepository.getEpisodeDetails(url)
                val airDate = episodeResponse.airDate
                callback(airDate)
            } catch (e: Exception) {
                // Логируем ошибку, чтобы понять, что пошло не так
                Log.e("CharacterDetailFragment", "Error fetching episode details: ${e.message}")
                callback("N/A")
            }
        }
    }
    private fun getEpisodeDetails(url: String, callback: (EpisodeResponse) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val episodeResponse = episodeRepository.getEpisodeDetails(url)
                Log.d("CharacterDetailFragment", "Fetched episode details: $episodeResponse")
                callback(episodeResponse)
            } catch (e: Exception) {
                // Логируем ошибку, чтобы понять, что пошло не так
                Log.e("CharacterDetailFragment", "Error fetching episode details: ${e.message}")
            }
        }
    }


}
