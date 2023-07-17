package com.rezapour.dogsbreeds.features.favoritelist

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.TestDispatcherProvider
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.usecase.BreedDetailUseCase
import com.rezapour.dogsbreeds.domain.usecase.FavoriteUseCase
import com.rezapour.dogsbreeds.util.sampels.DomainDataSampleFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class FavoriteViewModelTest{
    private lateinit var favoriteViewModel: FavoriteViewModel

    private val favoriteUseCase: FavoriteUseCase = mock()

    private val breedDetailUseCase: BreedDetailUseCase = mock()

    private val testScheduler = TestCoroutineScheduler()

    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider(testScheduler)


    @Test
    fun getBreedSuccess() = runTest(UnconfinedTestDispatcher(testScheduler)) {
        val apiFlow: Flow<List<BreedDomain>> =
            flow {
                emit(DomainDataSampleFactory.getBreedList())
            }
        whenever(favoriteUseCase.getFavorite()).thenReturn(apiFlow)

        favoriteViewModel =
            FavoriteViewModel(
                favoriteUseCase,
                breedDetailUseCase,
                dispatcherProvider
            )

        favoriteViewModel.uiState.test {
            Truth.assertThat(awaitItem()).isInstanceOf(DataState.Success::class.java)
        }

    }

    @Test
    fun getBreedError() = runTest(UnconfinedTestDispatcher(testScheduler)) {
        whenever(favoriteUseCase.getFavorite()).thenThrow(DataProviderException::class.java)

        favoriteViewModel =
            FavoriteViewModel(
                favoriteUseCase,
                breedDetailUseCase,
                dispatcherProvider
            )

        favoriteViewModel.uiState.test {
            Truth.assertThat(awaitItem()).isInstanceOf(DataState.Loading::class.java)
        }

    }
}