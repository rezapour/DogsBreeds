package com.rezapour.dogsbreeds.data.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteDataBaseEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") var id: Int = 0,
    @ColumnInfo("breed") var name: String,
    @ColumnInfo("type") var type: String?
)