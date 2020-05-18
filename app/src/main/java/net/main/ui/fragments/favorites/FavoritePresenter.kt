package net.main.ui.fragments.favorites


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.main.data.model.MovieExample
import net.main.data.repository.local.LocalRepository

class FavoritePresenter(private val view: View, private val localRepository: LocalRepository) {

    fun init() {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.IO) {
                localRepository.getFavorite()
            }
            view.showFavorites(movies)
        }
    }

    fun deleteFavorites() {
        CoroutineScope(Dispatchers.Main).launch {
            val movie = withContext(Dispatchers.IO) {
                localRepository.deleteAll()
                localRepository.getFavorite()
            }
            view.showFavorites(movie)
        }
    }

    interface View {
        fun showFavorites(movieExample: List<MovieExample>)
        fun showError(message: String)
        fun deleteAll() {
        }
    }
}