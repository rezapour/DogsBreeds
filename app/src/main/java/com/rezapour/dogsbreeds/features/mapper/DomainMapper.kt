package com.rezapour.dogsbreeds.features.mapper

import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import javax.inject.Inject

class DomainMapper @Inject constructor() {

    fun breedToBreedDomain(breed: Breed, favorite: Boolean) = BreedDomain(
        name = breed.name,
        type = breed.type,
        favorite = favorite,
        title = breed.title
    )

    fun breedDomainToBreed(breed: BreedDomain) = Breed(
        name = breed.name,
        type = breed.type,
    )

}