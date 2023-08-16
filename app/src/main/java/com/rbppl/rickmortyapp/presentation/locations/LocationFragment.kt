package com.rbppl.rickmortyapp.presentation.locations

import LocationDetailFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbppl.rickmortyapp.R
import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService
import com.rbppl.rickmortyapp.data.repository.LocationsRepositoryImpl
import com.rbppl.rickmortyapp.databinding.FragmentLocationsBinding
import com.rbppl.rickmortyapp.domain.GetLocationsUseCase
import com.rbppl.rickmortyapp.domain.model.Location

class LocationsFragment : Fragment(), LocationsAdapter.OnItemClickListener {

    private lateinit var viewModel: LocationsViewModel
    private lateinit var binding: FragmentLocationsBinding
    private lateinit var locationsAdapter: LocationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationsRepository = LocationsRepositoryImpl(RickAndMortyApiService)
        val getLocationsUseCase = GetLocationsUseCase(locationsRepository)
        viewModel = ViewModelProvider(this, LocationsViewModelFactory(getLocationsUseCase)).get(LocationsViewModel::class.java)

        setupRecyclerView()
        observeLocations()
    }

    private fun setupRecyclerView() {
        locationsAdapter = LocationsAdapter(emptyList(), this) // Пока нет данных
        binding.locationsRecyclerView.apply {
            adapter = locationsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeLocations() {
        viewModel.locations.observe(viewLifecycleOwner) { locations ->
            locationsAdapter.locationsList = locations
        }
    }

    override fun onItemClick(location: Location) {
        val bundle = Bundle()
        bundle.putSerializable("location", location)

        val locationDetailFragment = LocationDetailFragment()
        locationDetailFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, locationDetailFragment) // Замените "fragmentContainer" на id вашего контейнера для фрагментов
            .addToBackStack(null)
            .commit()
    }
}
