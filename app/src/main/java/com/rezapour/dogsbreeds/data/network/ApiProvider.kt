package com.rezapour.dogsbreeds.data.network

import com.rezapour.dogsbreeds.data.network.models.BreedsImageNetworkEntity
import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity

interface ApiProvider {

    suspend fun getBreeds(): BreedsNetworkEntity

    suspend fun getBreedsImages(breedName:String):BreedsImageNetworkEntity

}