package com.elhady.klivvr.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.elhady.klivvr.databinding.FragmentCityBinding
import com.elhady.klivvr.util.openLocationInGoogleMaps
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityFragment : Fragment() {
    private lateinit var binding: FragmentCityBinding
    private lateinit var adapter: CityAdapter
    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectEvent()
//        collectHomeData()
    }

//    private fun collectHomeData() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.state.collect {
//                adapter.setList(it.cities)
//            }
//        }
//    }

    private fun setAdapter() {
        adapter = CityAdapter(mutableListOf(), viewModel)
        binding.recyclerCity.adapter = adapter
    }


    private fun collectEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collectLatest { onEvent(it) }
            }
        }
    }

    private fun onEvent(event: CityUiEvent) {
        when (event) {
            is CityUiEvent.ClickOpenMapEvent -> {
                openLocationInGoogleMaps(event.city.coord.lat, event.city.coord.lon)
            }

            is CityUiEvent.ShowErrorEvent -> Snackbar.make(requireView(), event.error, Snackbar.LENGTH_LONG).show()
        }
    }
}