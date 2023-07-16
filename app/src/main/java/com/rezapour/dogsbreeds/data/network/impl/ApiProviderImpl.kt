package com.rezapour.dogsbreeds.data.network.impl

import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.data.network.ApiProvider
import com.rezapour.dogsbreeds.data.network.exception.ExceptionMapper
import com.rezapour.dogsbreeds.data.network.models.BreedsImageNetworkEntity
import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity
import com.rezapour.dogsbreeds.data.network.retrofit.ApiService
import javax.inject.Inject

class ApiProviderImpl @Inject constructor(private val apiService: ApiService) : ApiProvider {


    override suspend fun getBreeds(): BreedsNetworkEntity {
        try {
            val assetResponse = apiService.getBreeds()
            if (assetResponse.isSuccessful)
                if (assetResponse.isResponseValid())
                    return assetResponse.body()!!
                else
                    throw DataProviderException(ExceptionMapper.toRespondIsEmpty())
            else
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(assetResponse.code()))
        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }

    override suspend fun getBreedsImages(breedName: String): BreedsImageNetworkEntity {
        try {
            val assetResponse = apiService.getBreedsImages(breedName)
            if (assetResponse.isSuccessful)
                if (assetResponse.isResponseValid())
                    return assetResponse.body()!!
                else
                    throw DataProviderException(ExceptionMapper.toRespondIsEmpty())
            else
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(assetResponse.code()))
        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }
}