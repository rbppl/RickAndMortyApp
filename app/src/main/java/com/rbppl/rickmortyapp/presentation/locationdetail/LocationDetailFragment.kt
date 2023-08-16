import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbppl.rickmortyapp.data.remote.ResidentResponse
import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService
import com.rbppl.rickmortyapp.databinding.FragmentLocationDetailBinding
import com.rbppl.rickmortyapp.domain.model.Location
import com.rbppl.rickmortyapp.presentation.locationdetail.ResidentInfo
import com.rbppl.rickmortyapp.presentation.locationdetail.ResidentsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding
    private val apiService = RickAndMortyApiService.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val location = arguments?.getSerializable("location") as Location

        binding.locationDetailName.text = location.name
        binding.locationDetailType.text = location.type
        binding.locationDetailDimension.text = location.dimension

        val residentsRecyclerView = binding.residentsRecyclerView
        val residentsAdapter = ResidentsAdapter(emptyList())
        residentsRecyclerView.adapter = residentsAdapter
        residentsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.backButton2.setOnClickListener {
            requireActivity().onBackPressed()
        }
        fetchResidentsInfo(location.residents, residentsAdapter)
    }

    private fun fetchResidentsInfo(residents: List<String>, residentsAdapter: ResidentsAdapter) {
        CoroutineScope(Dispatchers.Main).launch {
            val residentsInfo = mutableListOf<ResidentInfo>()

            for ((index, residentLink) in residents.take(5).withIndex()) {
                val residentResponse = getResidentDetails(residentLink)
                if (residentResponse != null) {
                    val residentInfo = ResidentInfo(residentResponse.name, residentResponse.image)
                    residentsInfo.add(residentInfo)
                }
            }

            residentsAdapter.setData(residentsInfo)
        }
    }


    private suspend fun getResidentDetails(residentLink: String): ResidentResponse? {
        try {
            val response = apiService.getResidentDetails(residentLink)
            return response
        } catch (e: Exception) {
            Log.e("LocationDetailFragment", "Error fetching resident details: ${e.message}")
            return null
        }
    }
}
