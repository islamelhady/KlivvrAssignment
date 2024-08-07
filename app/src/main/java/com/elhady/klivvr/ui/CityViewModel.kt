package com.elhady.klivvr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.klivvr.domain.usecase.SortedAlphabeticalCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val sortedAlphabeticalCitiesUseCase: SortedAlphabeticalCitiesUseCase): ViewModel() {


    init {
        getCitiesByName()
    }
    private fun getCitiesByName(){
        viewModelScope.launch {
            val cityList = sortedAlphabeticalCitiesUseCase
            Log.d(TAG, "getCitiesSize: ${cityList.invoke().size}")
        }
    }
    companion object{
        private const val TAG = "CityViewModel"
    }
}