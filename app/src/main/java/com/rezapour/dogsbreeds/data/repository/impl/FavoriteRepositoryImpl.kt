package com.rezapour.dogsbreeds.data.repository.impl

import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.database.mapper.DataBaseMapper
import com.rezapour.dogsbreeds.data.database.room.dao.FavoriteDao
import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.repository.FavoriteRepository
import com.rezapour.officemanager.base.dispatcher.impl.DispatcherProviderImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val mapper: DataBaseMapper,
    private val dispatcherProviderImpl: DispatcherProvider
) :
    FavoriteRepository {
    override fun getFavorite(): Flow<List<Breed>> = dao.getFavorites()
        .map { entities -> mapper.favoriteDataBaseEntityListToBreedList(entities) }
        .flowOn(dispatcherProviderImpl.io)

    override suspend fun addFavorite(breed: Breed) = withContext(dispatcherProviderImpl.io) {
        dao.insertFavorite(mapper.breedToFavoriteDataBaseEntity(breed))
    }

    override suspend fun deleteFavorite(breed: Breed) {
        dao.deleteFavorite(breed.title)
    }
}