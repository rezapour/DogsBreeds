package com.rezapour.dogsbreeds.data.database.mapper

import com.rezapour.dogsbreeds.data.database.room.entities.FavoriteDataBaseEntity
import com.rezapour.dogsbreeds.domain.model.Breed
import javax.inject.Inject

class DataBaseMapper @Inject constructor() {

    fun favoriteDataBaseEntityToBreed(entity: FavoriteDataBaseEntity): Breed = with(entity) {
        Breed(name = name, type = type)
    }

    fun breedToFavoriteDataBaseEntity(domain: Breed): FavoriteDataBaseEntity = with(domain) {
        FavoriteDataBaseEntity(name = name, type = type)
    }

    fun favoriteDataBaseEntityListToBreedList(entities: List<FavoriteDataBaseEntity>): List<Breed> =
        entities.map { entity -> favoriteDataBaseEntityToBreed(entity) }

}