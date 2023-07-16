package com.example.radiusandroidapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "radius_all_products")
data class RadiusAllProduct(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("facilities") val facilities: List<Facilities> = emptyList(),
    @SerializedName("exclusions") val exclusions: List<List<Exclusions>> = emptyList()
)

data class Facilities(
    @SerializedName("facility_id") val facilityId: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("options") val options: List<Options> = emptyList()
)

data class Options(
    @SerializedName("name") val name: String? = null,
    @SerializedName("icon") val icon: String? = null,
    @SerializedName("id") val id: String? = null
)

data class Exclusions(
    @SerializedName("facility_id") val facilityId: String? = null,
    @SerializedName("options_id") val optionsId: String? = null
)

