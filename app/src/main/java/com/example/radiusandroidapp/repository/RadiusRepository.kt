package com.example.radiusandroidapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.radiusandroidapp.api.RadiusClient
import com.example.radiusandroidapp.model.RadiusAllProduct
import com.example.radiusandroidapp.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class RadiusRepository @Inject constructor(private val radiusClient: RadiusClient) {

    private val _radiusLiveData = MutableLiveData<NetworkResult<RadiusAllProduct>>()
    val radiusLiveData get() = _radiusLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val statusLiveData get() = _statusLiveData

    suspend fun getAllProduct() {
        _radiusLiveData.postValue(NetworkResult.Loading())
        val response = radiusClient.getAllProduct()
        if (response.isSuccessful && response.body() != null) {
            _radiusLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _radiusLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _radiusLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }


}