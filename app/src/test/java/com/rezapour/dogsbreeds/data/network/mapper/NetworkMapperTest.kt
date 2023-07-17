package com.rezapour.dogsbreeds.data.network.mapper

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import com.rezapour.dogsbreeds.util.sampels.NetworkSampleFactory
import org.junit.Before
import org.junit.Test


class NetworkMapperTest {
    lateinit var networkMapper: NetworkMapper

    @Before
    fun setup() {
        networkMapper = NetworkMapper()
    }

    @Test
    fun breedNetworkEntityToBreed() {
        val respond= networkMapper.breedNetworkEntityToBreed(NetworkSampleFactory.getBreedNetworkEntity())
        assertThat(respond).isEqualTo(DataSampleFactory.getBreedList())
    }

}