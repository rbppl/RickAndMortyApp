package com.rbppl.rickmortyapp.data.repository

import com.rbppl.rickmortyapp.data.remote.RickAndMortyApi
import com.rbppl.rickmortyapp.data.remote.EpisodeResponse

class EpisodeRepository(private val api: RickAndMortyApi) {

    suspend fun getEpisodeDetails(url: String): EpisodeResponse {
        return api.getEpisodeDetails(url)
    }

}
