package com.rezapour.dogsbreeds.util.sampels

import com.rezapour.dogsbreeds.domain.model.Breed

object DataSampleFactory {
    fun getBreedList(): List<Breed> {
        return listOf(
            Breed(name = "affenpinscher"),
            Breed(name = "african"),
            Breed(name = "bulldog", type = "boston"),
            Breed(name = "bulldog", type = "english"),
            Breed(name = "bulldog", type = "french")
        )
    }
}