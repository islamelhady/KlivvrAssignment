package com.elhady.klivvr.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elhady.klivvr.databinding.FragmentCityBinding
import dagger.hilt.android.AndroidEntryPoint

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
        adapter = CityAdapter(mutableListOf(), viewModel)
        binding.recyclerCity.adapter = adapter
        return binding.root

    }
}