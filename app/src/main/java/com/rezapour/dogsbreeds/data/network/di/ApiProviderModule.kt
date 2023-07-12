package com.rezapour.dogsbreeds.data.network.di

import com.rezapour.dogsbreeds.data.network.ApiProvider
import com.rezapour.dogsbreeds.data.network.impl.ApiProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApiProviderModule {

    @Singleton
    @Binds
    fun bindDispatcherProvider(impl: ApiProviderImpl): ApiProvider
}