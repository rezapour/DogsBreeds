package com.rezapour.dogsbreeds.data.network.retrofit

import com.rezapour.dogsbreeds.data.network.models.BreedsImageNetworkEntity
import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<BreedsNetworkEntity>

    @GET("breed/{breed}/images")
    suspend fun getBreedsImages(@Path("breed") breedName: String): Response<BreedsImageNetworkEntity>
}