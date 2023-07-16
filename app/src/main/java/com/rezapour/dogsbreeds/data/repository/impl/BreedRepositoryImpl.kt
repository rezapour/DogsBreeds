package com.rezapour.dogsbreeds.data.repository.impl

import android.util.Log
import com.rezapour.dogsbreeds.data.network.ApiProvider
import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.network.mapper.NetworkMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val apiProvider: ApiProvider,
    private val dispatcherProvider: DispatcherProvider,
    private val networkMapper: NetworkMapper
) : BreedRepository {
    override suspend fun getBreeds(): Flow<List<Breed>> = flow {
        Log.d(
            "REZAAPP",
            networkMapper.breedNetworkEntityToBreed(apiProvider.getBreeds()).toString()
        )
        emit(networkMapper.breedNetworkEntityToBreed(apiProvider.getBreeds()))
    }.flowOn(dispatcherProvider.io)

    override suspend fun getBreedsImages(breedName: String): List<String> =
        withContext(dispatcherProvider.io) {
            apiProvider.getBreedsImages(breedName).message
        }


}