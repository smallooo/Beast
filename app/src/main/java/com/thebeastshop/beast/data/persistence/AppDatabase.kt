package com.thebeastshop.beast.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thebeastshop.beast.data.model.Poster

@Database(entities = [Poster::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun posterDao(): PosterDao
}