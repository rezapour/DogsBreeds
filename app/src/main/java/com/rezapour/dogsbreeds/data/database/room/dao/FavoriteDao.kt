package com.rezapour.dogsbreeds.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rezapour.dogsbreeds.data.database.room.entities.FavoriteDataBaseEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(breed: FavoriteDataBaseEntity)


    @Query("DELETE FROM favorite WHERE  title= :title")
    suspend fun deleteFavorite(title: String)

    @Query("SELECT * FROM FAVORITE")
    fun getFavorites(): Flow<List<FavoriteDataBaseEntity>>
}