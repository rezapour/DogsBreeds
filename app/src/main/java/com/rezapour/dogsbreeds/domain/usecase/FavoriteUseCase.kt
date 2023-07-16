package com.rezapour.dogsbreeds.domain.usecase

import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.repository.FavoriteRepository
import com.rezapour.dogsbreeds.features.mapper.DomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val mapper: DomainMapper
) {

    fun getFavorite(): Flow<List<BreedDomain>> =
        favoriteRepository.getFavorite()
            .map { breeds -> breeds.map { breed -> mapper.breedToBreedDomain(breed, true) } }

    suspend fun addFavorite(breedDomain: BreedDomain) {
        favoriteRepository.addFavorite(mapper.breedDomainToBreed(breedDomain))
    }

    suspend fun deleteFavorite(breedDomain: BreedDomain) {
        favoriteRepository.deleteFavorite(mapper.breedDomainToBreed(breedDomain))
    }

}