package com.rezapour.dogsbreeds.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rezapour.dogsbreeds.data.database.room.dao.FavoriteDao
import com.rezapour.dogsbreeds.data.database.room.entities.FavoriteDataBaseEntity

@Database(entities = [FavoriteDataBaseEntity::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}