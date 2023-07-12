package com.rezapour.dogsbreeds.data.network

import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity

interface ApiProvider {

    suspend fun getBreeds(): BreedsNetworkEntity

}