package com.rezapour.dogsbreeds.util.sampels

import com.rezapour.dogsbreeds.data.database.room.entities.FavoriteDataBaseEntity

object DataBaseSampleFactory {

    fun getFavoriteDataBaseEntity() = FavoriteDataBaseEntity(
        title = "boston bulldog",
        name = "bulldog",
        type = "boston"
    )

}