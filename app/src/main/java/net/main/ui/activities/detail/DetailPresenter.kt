package net.main.ui.activities.detail

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.main.data.model.MovieExample
import net.main.data.repository.local.LocalRepository

class DetailPresenter(private val view: View, private val localRepository: LocalRepository) {
    fun init(id: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            val inFav = withContext(Dispatchers.IO) {
                localRepository.favoriteCheck(id)
            }
            view.movieInFav(inFav)
        }
    }

    fun starClicker(isFav: Boolean, movieExample: MovieExample) {
        if (isFav) {
            addMovie(movieExample)
        } else {
            deleteMovie(movieExample)
        }
    }

    private fun addMovie(movieExample: MovieExample) {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.IO) {
                localRepository.insertFavorite(movieExample)
                localRepository.getFavorite()
            }
        }
    }

    private fun deleteMovie(movieExample: MovieExample) {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.IO) {
                localRepository.deleteFavorite(movieExample)
                localRepository.getFavorite()
            }
        }
    }


    interface View {
        fun showError(message: String)
        fun movieInFav(inFav: Boolean)
    }
}