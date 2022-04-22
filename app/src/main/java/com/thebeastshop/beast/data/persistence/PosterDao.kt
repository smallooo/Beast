package com.thebeastshop.beast.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thebeastshop.beast.data.model.Poster

@Dao
interface PosterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosterList(posters: List<Poster>)

    @Query("SELECT * FROM Poster WHERE id = :id_")
    suspend fun getPoster(id_: Long): Poster?

    @Query("SELECT * FROM Poster")
    suspend fun getPosterList(): List<Poster>
}