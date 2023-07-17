package com.rezapour.dogsbreeds.data.repository.impl

import com.google.common.truth.Truth.assertThat
import com.rezapour.dogsbreeds.base.TestDispatcherProvider
import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import com.rezapour.dogsbreeds.data.database.mapper.DataBaseMapper
import com.rezapour.dogsbreeds.data.database.room.dao.FavoriteDao
import com.rezapour.dogsbreeds.data.database.room.entities.FavoriteDataBaseEntity
import com.rezapour.dogsbreeds.domain.repository.FavoriteRepository
import com.rezapour.dogsbreeds.util.sampels.DataBaseSampleFactory
import com.rezapour.dogsbreeds.util.sampels.DataSampleFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteRepositoryImplTest {
    private val dao: FavoriteDao = mock()
    private lateinit var favoriteRepository: FavoriteRepository
    private val testScheduler = TestCoroutineScheduler()
    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider(testScheduler)

    @Before
    fun setup() {
        favoriteRepository = FavoriteRepositoryImpl(dao, DataBaseMapper(), dispatcherProvider)
    }

    @Test
    fun getFavoriteTest() = runTest {

        val flow: Flow<List<FavoriteDataBaseEntity>> =
            flow {
                emit(listOf(DataBaseSampleFactory.getFavoriteDataBaseEntity()))
            }
        whenever(dao.getFavorites()).thenReturn(flow)


        favoriteRepository.getFavorite().collect {
            assertThat(it).isEqualTo(listOf(DataSampleFactory.getBreed()))
        }
    }
}
