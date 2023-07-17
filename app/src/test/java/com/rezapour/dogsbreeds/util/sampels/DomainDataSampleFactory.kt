package com.rezapour.dogsbreeds.util.sampels

import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.model.BreedDomain

object DomainDataSampleFactory {

    fun getBreedList(): List<BreedDomain> {
        return listOf(
            BreedDomain(name = "affenpinscher", title = "affenpinscher", favorite = false),
            BreedDomain(name = "african", title = "african", favorite = false),
            BreedDomain(
                name = "bulldog",
                type = "boston",
                title = "boston bulldog",
                favorite = true
            ),
            BreedDomain(
                name = "bulldog",
                type = "english",
                title = "english bulldog",
                favorite = false
            ),
            BreedDomain(
                name = "bulldog",
                type = "french",
                title = "french bulldog",
                favorite = false
            )
        )
    }

    fun getBreed() =
        BreedDomain(name = "bulldog", type = "boston", title = "boston bulldog", favorite = true)

    fun getImageList() = listOf(
        "https://images.dog.ceo/breeds/bulldog-boston/20200710_175933.jpg",
        "https://images.dog.ceo/breeds/bulldog-boston/20200710_175944.jpg",
        "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg",
        "https://images.dog.ceo/breeds/bulldog-french/n02096585_2082.jpg"
    )

    fun getImageListFiltered() = listOf(
        "https://images.dog.ceo/breeds/bulldog-boston/20200710_175933.jpg",
        "https://images.dog.ceo/breeds/bulldog-boston/20200710_175944.jpg",
        "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg",
    )
}