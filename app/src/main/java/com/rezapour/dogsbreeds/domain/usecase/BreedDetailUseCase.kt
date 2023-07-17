package com.rezapour.dogsbreeds.domain.usecase

import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BreedDetailUseCase @Inject constructor(private val breedRepository: BreedRepository) {

    private val _breedState: MutableStateFlow<BreedDomain> =
        MutableStateFlow(BreedDomain("", "", "", false))
    val breedState: StateFlow<BreedDomain> = _breedState

    suspend fun getBreedImages(breed: BreedDomain): List<String> =
        breedRepository.getBreedsImages(breed.name)
            .filter { url ->
                if (breed.type == null)
                    true
                else
                    url.contains(breed.type)
            }

    fun upDataData(breed: BreedDomain) {
        _breedState.value = breed
    }

}