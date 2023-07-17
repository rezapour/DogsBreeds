package com.rezapour.dogsbreeds.data.repository.impl

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.base.TestDispatcherProvider
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.network.ApiProvider
import com.rezapour.dogsbreeds.data.network.mapper.NetworkMapper
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import com.rezapour.dogsbreeds.util.MainCoroutineRule
import com.rezapour.dogsbreeds.util.sampels.DataBaseSampleFactory
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import com.rezapour.dogsbreeds.util.sampels.NetworkSampleFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class BreedRepositoryImplTest {
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    private val apiProvider: ApiProvider = mock()
    private lateinit var breedRepository: BreedRepository
    private val testScheduler = TestCoroutineScheduler()
    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider(testScheduler)

    @Before
    fun setup() {
        breedRepository = BreedRepositoryImpl(apiProvider, dispatcherProvider, NetworkMapper())
    }

    @Test
    fun getBreedsTest() = runTest {
        whenever(apiProvider.getBreeds()).thenReturn(NetworkSampleFactory.getBreedNetworkEntity())

        breedRepository.getBreeds().collect {
            assertThat(it).isEqualTo(DataSampleFactory.getBreedList())
        }
    }

    @Test
    fun getBreedsImage() = runTest {
        whenever(apiProvider.getBreedsImages(any())).thenReturn(NetworkSampleFactory.getBreedsImageNetworkEntity())

        val response = breedRepository.getBreedsImages("")
        assertThat(response).isEqualTo(DataSampleFactory.getListOfImages())

    }
}