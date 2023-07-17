package com.rezapour.dogsbreeds.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import com.rezapour.dogsbreeds.util.sampels.DomainDataSampleFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class BreedDetailUseCaseTest {
    private val breedRepository: BreedRepository = mock()

    private lateinit var breedDetailUseCase: BreedDetailUseCase

    @Before
    fun setup() {
        breedDetailUseCase = BreedDetailUseCase(breedRepository)
    }

    @Test
    fun getImageWithType() = runTest {
        whenever(breedRepository.getBreedsImages(any())).thenReturn(DomainDataSampleFactory.getImageList())
        val respond = breedDetailUseCase.getBreedImages(
            BreedDomain(
                title = "bulldog-boston",
                name = "bulldog",
                type = "boston",
                favorite = false
            )
        )

        assertThat(respond).isEqualTo(DomainDataSampleFactory.getImageListFiltered())
    }

    @Test
    fun getImageWithoutType() = runTest {
        whenever(breedRepository.getBreedsImages(any())).thenReturn(DomainDataSampleFactory.getImageList())
        val respond = breedDetailUseCase.getBreedImages(
            BreedDomain(
                title = "bulldog-boston",
                name = "bulldog",
                favorite = false
            )
        )

        assertThat(respond).isEqualTo(DomainDataSampleFactory.getImageList())
    }
}