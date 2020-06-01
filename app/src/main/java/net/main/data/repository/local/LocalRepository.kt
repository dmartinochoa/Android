package net.main.data.repository.local

import net.main.data.model.MovieExample

interface LocalRepository {
    suspend fun getFavorite(): List<MovieExample>

    suspend fun insertFavorite(movie: MovieExample)

    suspend fun deleteFavorite(movieExample: MovieExample)

    suspend fun searchId(id: Long): List<MovieExample>

    suspend fun favoriteCheck(id: Long): Boolean

    suspend fun deleteAll()
}