package com.rezapour.dogsbreeds.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import com.rezapour.dogsbreeds.domain.repository.FavoriteRepository
import com.rezapour.dogsbreeds.features.mapper.DomainMapper
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import com.rezapour.dogsbreeds.util.sampels.DomainDataSampleFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class BreedUseCaseTest {

    private val breedRepository: BreedRepository = mock()

    private val favoriteRepository: FavoriteRepository = mock()

    private lateinit var breedUseCase: BreedUseCase

    @Before
    fun setup() {
        breedUseCase = BreedUseCase(breedRepository, favoriteRepository, DomainMapper())
    }

    @Test
    fun getBreedDomain() = runTest {
        val apiFlow: Flow<List<Breed>> =
            flow {
                emit(DataSampleFactory.getBreedList())
            }
        whenever(breedRepository.getBreeds()).thenReturn(apiFlow)

        val favoriteFlow: Flow<List<Breed>> =
            flow {
                emit(listOf(DataSampleFactory.getBreed()))
            }
        whenever(favoriteRepository.getFavorite()).thenReturn(favoriteFlow)


        breedUseCase.getBreed().collect{
            assertThat(it).isEqualTo(DomainDataSampleFactory.getBreedList())
        }
    }

}