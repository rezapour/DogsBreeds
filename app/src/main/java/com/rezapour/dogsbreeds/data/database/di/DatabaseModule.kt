package com.rezapour.dogsbreeds.data.database.di

import android.content.Context
import androidx.room.Room
import com.rezapour.dogsbreeds.data.Constants
import com.rezapour.dogsbreeds.data.database.room.AppDataBase
import com.rezapour.dogsbreeds.data.database.room.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            Constants.DATA_BASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideAssetDao(appDataBase: AppDataBase): FavoriteDao {
        return appDataBase.favoriteDao()
    }
}