package com.rbppl.rickmortyapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbppl.rickmortyapp.R
import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService
import com.rbppl.rickmortyapp.data.repository.CharactersRepositoryImpl
import com.rbppl.rickmortyapp.domain.GetCharactersUseCase
import com.rbppl.rickmortyapp.databinding.FragmentCharactersBinding
import com.rbppl.rickmortyapp.domain.model.Character
import com.rbppl.rickmortyapp.presentation.characterdetail.CharacterDetailFragment


class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val charactersRepository = CharactersRepositoryImpl(RickAndMortyApiService)
        val getCharactersUseCase = GetCharactersUseCase(charactersRepository)
        viewModel = ViewModelProvider(this, CharactersViewModelFactory(getCharactersUseCase)).get(CharactersViewModel::class.java)

        setupRecyclerView()
        observeCharacters()
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharactersAdapter(emptyList())
        charactersAdapter.setOnItemClickListener { character ->
            showCharacterDetail(character)
        }

        binding.charactersRecyclerView.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeCharacters() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            charactersAdapter.charactersList = characters
        }
    }

    private fun showCharacterDetail(character: Character) {
        val bundle = Bundle()
        bundle.putSerializable("character", character)
        val detailFragment = CharacterDetailFragment()
        detailFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
