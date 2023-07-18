package com.example.radiusandroidapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiusandroidapp.db.RadiusAllProductDao
import com.example.radiusandroidapp.model.RadiusAllProduct
import com.example.radiusandroidapp.repository.RadiusRepository
import com.example.radiusandroidapp.utils.Constant
import com.example.radiusandroidapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadiusViewModel @Inject constructor(
    private val radiusRepository: RadiusRepository,
    private val radiusAllProductDao: RadiusAllProductDao
) : ViewModel() {

    private val _radiusLiveData = MutableLiveData<NetworkResult<RadiusAllProduct>>()
    val radiusLiveData: LiveData<NetworkResult<RadiusAllProduct>> get() = _radiusLiveData

    fun getAllProduct() {
        viewModelScope.launch {
            _radiusLiveData.value = NetworkResult.Loading()

            // Fetch data from the database
            val radiusAllProduct = radiusAllProductDao.getRadiusAllProducts().firstOrNull()
            Log.d(Constant.TAG, "getAllProductBodyDataViewModel: $radiusAllProduct")
            if (radiusAllProduct != null) {
                _radiusLiveData.value = NetworkResult.Success(radiusAllProduct)
            }

            // Make the network request to update data
            radiusRepository.getAllProduct()
        }
    }
}
