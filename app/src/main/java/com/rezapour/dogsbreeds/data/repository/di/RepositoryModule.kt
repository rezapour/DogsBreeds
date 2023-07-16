package com.rezapour.dogsbreeds.data.repository.di

import com.rezapour.dogsbreeds.data.repository.impl.BreedRepositoryImpl
import com.rezapour.dogsbreeds.data.repository.impl.FavoriteRepositoryImpl
import com.rezapour.dogsbreeds.domain.repository.BreedRepository
import com.rezapour.dogsbreeds.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun breedRepositoryProvider(impl: BreedRepositoryImpl): BreedRepository

    @Binds
    fun favoriteRepositoryProvider(impl: FavoriteRepositoryImpl): FavoriteRepository
}