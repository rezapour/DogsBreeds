package com.rezapour.dogsbreeds.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BreedsNetworkEntity(
    @Expose @SerializedName("message") var message: Any?,
    @Expose @SerializedName("status") var status: String? = null
)