package com.rezapour.dogsbreeds.base

import com.rezapour.dogsbreeds.base.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider(testScheduler: TestCoroutineScheduler) : DispatcherProvider {
    override val main: CoroutineDispatcher = StandardTestDispatcher(testScheduler)
    override val io: CoroutineDispatcher = UnconfinedTestDispatcher(testScheduler)
    override val bg: CoroutineDispatcher = StandardTestDispatcher(testScheduler)
}