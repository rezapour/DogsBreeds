package com.rezapour.dogsbreeds.features.mapper

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import com.rezapour.dogsbreeds.util.sampels.DomainDataSampleFactory
import org.junit.Before
import org.junit.Test


class DomainMapperTest {
    lateinit var domainMapper: DomainMapper

    @Before
    fun setup() {
        domainMapper = DomainMapper()
    }

    @Test
    fun breedToBreedDomainTest() {
        val domain = domainMapper.breedToBreedDomain(DataSampleFactory.getBreed(), true)
        assertThat(domain).isEqualTo(DomainDataSampleFactory.getBreed())
    }

    @Test
    fun breedDomainToBreedTest() {
        val breed = domainMapper.breedDomainToBreed(DomainDataSampleFactory.getBreed())
        assertThat(breed).isEqualTo(DataSampleFactory.getBreed())
    }
}