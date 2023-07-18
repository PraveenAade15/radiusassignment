package com.example.radiusandroidapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.radiusandroidapp.api.RadiusClient
import com.example.radiusandroidapp.db.RadiusAllProductDao
import com.example.radiusandroidapp.model.RadiusAllProduct
import com.example.radiusandroidapp.utils.Constant.TAG
import com.example.radiusandroidapp.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class RadiusRepository @Inject constructor(
    private val radiusClient: RadiusClient,
    private val radiusAllProductDao: RadiusAllProductDao
) {
    private val _radiusLiveData = MutableLiveData<NetworkResult<RadiusAllProduct>>()
    val radiusLiveData: LiveData<NetworkResult<RadiusAllProduct>> get() = _radiusLiveData

    suspend fun getAllProduct() {
        _radiusLiveData.postValue(NetworkResult.Loading())
        val response = radiusClient.getAllProduct()
        if (response.isSuccessful && response.body() != null) {
            _radiusLiveData.postValue(NetworkResult.Success(response.body()!!))

            // Save the products to the database
            val radiusAllProduct = response.body()!!
            radiusAllProductDao.insertRadiusAllProduct(radiusAllProduct)
            Log.d(TAG, "getAllProductBodyData: $radiusAllProduct")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _radiusLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _radiusLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}
