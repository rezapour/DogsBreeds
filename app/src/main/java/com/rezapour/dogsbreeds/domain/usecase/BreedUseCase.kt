package com.rezapour.dogsbreeds.domain.usecase

import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import com.rezapour.dogsbreeds.domain.repository.FavoriteRepository
import com.rezapour.dogsbreeds.features.mapper.DomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedUseCase @Inject constructor(
    private val breedRepository: BreedRepository,
    private val favoriteRepository: FavoriteRepository,
    private val mapper: DomainMapper
) {

    suspend fun getBreed(): Flow<List<BreedDomain>> {
        return combine(
            breedRepository.getBreeds(),
            favoriteRepository.getFavorite()
        ) { breeds: List<Breed>, favorite: List<Breed> ->
            breeds.map { breed -> mapper.breedToBreedDomain(breed, favorite.contains(breed)) }
        }
    }

    suspend fun getBreedImages(breedName: String): List<String> =
        breedRepository.getBreedsImages(breedName)

}