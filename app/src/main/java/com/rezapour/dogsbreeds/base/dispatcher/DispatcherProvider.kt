package com.rezapour.dogsbreeds.base.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val bg: CoroutineDispatcher
}