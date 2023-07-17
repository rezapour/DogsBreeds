package com.rezapour.dogsbreeds.features.breedslist

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.TestDispatcherProvider
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.exception.DataProviderException
import com.rezapour.dogsbreeds.domain.model.Breed
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.domain.usecase.BreedDetailUseCase
import com.rezapour.dogsbreeds.domain.usecase.BreedUseCase
import com.rezapour.dogsbreeds.domain.usecase.FavoriteUseCase
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import com.rezapour.dogsbreeds.util.sampels.DomainDataSampleFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class BreedsListViewModelTest {
    private lateinit var breedsListViewModel: BreedsListViewModel

    private val breedUseCase: BreedUseCase = mock()

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
        whenever(breedUseCase.getBreed()).thenReturn(apiFlow)

        breedsListViewModel =
            BreedsListViewModel(
                breedUseCase,
                favoriteUseCase,
                breedDetailUseCase,
                dispatcherProvider
            )

        breedsListViewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(DataState.Success::class.java)
        }

    }

    @Test
    fun getBreedError() = runTest(UnconfinedTestDispatcher(testScheduler)) {
        whenever(breedUseCase.getBreed()).thenThrow(DataProviderException::class.java)

        breedsListViewModel =
            BreedsListViewModel(
                breedUseCase,
                favoriteUseCase,
                breedDetailUseCase,
                dispatcherProvider
            )
        runBlocking {
            breedsListViewModel.uiState.test {
                assertThat(awaitItem()).isInstanceOf(DataState.Loading::class.java)
            }
        }

    }


}