package com.rezapour.dogsbreeds.domain.repository

import com.rezapour.dogsbreeds.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavorite(): Flow<List<Breed>>

    suspend fun addFavorite(breed: Breed)

    suspend fun deleteFavorite(breed: Breed)

}