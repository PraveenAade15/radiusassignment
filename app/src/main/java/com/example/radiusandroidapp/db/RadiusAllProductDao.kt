package com.example.radiusandroidapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.radiusandroidapp.model.RadiusAllProduct
import kotlinx.coroutines.flow.Flow



@Dao
interface RadiusAllProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRadiusAllProduct(radiusAllProduct: RadiusAllProduct)

    @Query("SELECT * FROM radius_all_products")
    fun getRadiusAllProducts(): Flow<RadiusAllProduct?>
}


