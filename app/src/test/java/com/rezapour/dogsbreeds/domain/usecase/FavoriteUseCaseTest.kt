package com.rezapour.dogsbreeds.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.domain.model.Breed
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
class FavoriteUseCaseTest {
    private val favoriteRepository: FavoriteRepository = mock()

    private lateinit var favoriteUseCase: FavoriteUseCase


    @Before
    fun setup() {
        favoriteUseCase = FavoriteUseCase(favoriteRepository, DomainMapper())
    }

    @Test
    fun getFavoriteTest() = runTest {
        val favoriteFlow: Flow<List<Breed>> =
            flow {
                emit(listOf(DataSampleFactory.getBreed()))
            }
        whenever(favoriteRepository.getFavorite()).thenReturn(favoriteFlow)

        favoriteUseCase.getFavorite().collect {
            assertThat(it).isEqualTo(listOf(DomainDataSampleFactory.getBreed()))
        }
    }
}