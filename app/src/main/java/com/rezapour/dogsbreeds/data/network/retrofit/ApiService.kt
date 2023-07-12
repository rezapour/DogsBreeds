package com.rezapour.dogsbreeds.data.network.retrofit

import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreeds(): Response<BreedsNetworkEntity>
}