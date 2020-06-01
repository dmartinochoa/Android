package net.main.data.repository.local

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getFavorite(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movieEntity: MovieEntity)

    @Query("SELECT * from movies WHERE id= :id")
    suspend fun searchId(id: Long): List<MovieEntity>

    @Delete
    suspend fun deleteFavorite(movieEntity: MovieEntity)

    @Query("DELETE from movies")
    suspend fun deleteAll()

}