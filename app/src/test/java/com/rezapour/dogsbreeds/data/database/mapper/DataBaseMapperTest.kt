package com.rezapour.dogsbreeds.data.database.mapper

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.util.sampels.DataBaseSampleFactory
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import org.junit.Before
import org.junit.Test


class DataBaseMapperTest {
    lateinit var dataBaseMapper: DataBaseMapper

    @Before
    fun setup() {
        dataBaseMapper = DataBaseMapper()
    }

    @Test
    fun favoriteDataBaseEntityToBreed() {
        val response =
            dataBaseMapper.favoriteDataBaseEntityToBreed(DataBaseSampleFactory.getFavoriteDataBaseEntity())
        assertThat(response).isEqualTo(DataSampleFactory.getBreed())
    }

    @Test
    fun breedToFavoriteDataBaseEntity(){
        val response =
            dataBaseMapper.breedToFavoriteDataBaseEntity(DataSampleFactory.getBreed())
        assertThat(response).isEqualTo(DataBaseSampleFactory.getFavoriteDataBaseEntity())
    }

    @Test
    fun favoriteDataBaseEntityListToBreedList() {
        val response =
            dataBaseMapper.favoriteDataBaseEntityListToBreedList(listOf(DataBaseSampleFactory.getFavoriteDataBaseEntity()) )
        assertThat(response).isEqualTo(listOf(DataSampleFactory.getBreed()) )
    }
}