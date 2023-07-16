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

    //TODO Delete base on the breed couse problems maybe base on id would be better
    @Query("DELETE FROM favorite WHERE  breed= :breed")
    suspend fun deleteFavorite(breed: String)

    @Query("SELECT * FROM FAVORITE")
     fun getFavorites(): Flow<List<FavoriteDataBaseEntity>>
}