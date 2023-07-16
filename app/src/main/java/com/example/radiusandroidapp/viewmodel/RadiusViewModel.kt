package com.example.radiusandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiusandroidapp.repository.RadiusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadiusViewModel @Inject constructor(private val radiusRepository: RadiusRepository) : ViewModel() {

    val radiusLiveData get() = radiusRepository.radiusLiveData
    val statusLiveData get() = radiusRepository.statusLiveData
    fun getAllProduct() {
        viewModelScope.launch {
            radiusRepository.getAllProduct()
        }
    }
}