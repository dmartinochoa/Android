package net.main.data.repository.remote

import net.main.data.model.MovieExample
import net.main.data.repository.local.LocalRepository
import net.main.data.repository.local.MovieDatabase
import net.main.data.repository.local.MovieEntity

class RoomLocalRepository(private val movieDatabase: MovieDatabase) :
    LocalRepository {

    override suspend fun getFavorite(): List<MovieExample> {
        val movieEntity = movieDatabase.movieDao().getFavorite()
        return movieEntity.map {
            MovieExample(
                it.id,
                it.movieImg,
                it.movieName,
                it.movieScore,
                it.movieOriginalName,
                it.movieRelease,
                it.movieDesc
            )
        }
    }

    override suspend fun insertFavorite(movie: MovieExample) {
        movieDatabase.movieDao().insertFavorite(
            MovieEntity(
                id = movie.movieId,
                movieImg = movie.movieImg,
                movieName = movie.movieName,
                movieScore = movie.movieScore,
                movieOriginalName = movie.movieOriginalName,
                movieRelease = movie.movieRelease,
                movieDesc = movie.movieDesc
            )
        )
    }


    override suspend fun deleteFavorite(movieExample: MovieExample) {
        movieDatabase.movieDao().deleteFavorite(
            MovieEntity(
                id = movieExample.movieId,
                movieImg = movieExample.movieImg,
                movieName = movieExample.movieName,
                movieOriginalName = movieExample.movieOriginalName,
                movieRelease = movieExample.movieRelease,
                movieScore = movieExample.movieScore,
                movieDesc = movieExample.movieDesc
            )
        )
    }

    override suspend fun searchId(id: Long): List<MovieExample> {
        val movieEntity = movieDatabase.movieDao().searchId(id)
        val movieList = movieEntity.map {
            MovieExample(
                it.id,
                it.movieImg,
                it.movieName,
                it.movieScore,
                it.movieOriginalName,
                it.movieRelease,
                it.movieDesc
            )
        }
        return movieList
    }

    override suspend fun favoriteCheck(id: Long): Boolean {
        val movieEntity = searchId(id)
        return movieEntity.isNotEmpty()
    }

    override suspend fun deleteAll() {
        movieDatabase.movieDao().deleteAll()
    }
}
