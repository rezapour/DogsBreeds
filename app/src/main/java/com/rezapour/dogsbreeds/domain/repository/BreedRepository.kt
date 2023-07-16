package com.rezapour.dogsbreeds.domain.repository

import com.rezapour.dogsbreeds.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedRepository {

    suspend fun getBreeds(): Flow<List<Breed>>

    suspend fun getBreedsImages(breedName:String):List<String>
}