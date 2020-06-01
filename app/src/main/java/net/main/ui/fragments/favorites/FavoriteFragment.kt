package net.main.ui.fragments.favorites

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import net.main.data.model.MovieExample
import net.main.data.repository.local.MovieDatabaseFactory
import net.main.data.repository.remote.RoomLocalRepository
import net.main.ui.activities.detail.DetailActivity
import net.main.ui.adaptor.MovieAdapter
import net.test.R


class FavoriteFragment : Fragment(), FavoritePresenter.View {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var presenter: FavoritePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        val localRepository =
            context?.let { MovieDatabaseFactory.get(it) }?.let { RoomLocalRepository(it) }
        localRepository?.let { FavoritePresenter(this, it) }?.init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll -> deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localRepository =
            context?.let { MovieDatabaseFactory.get(it) }?.let { RoomLocalRepository(it) }
        localRepository?.let { FavoritePresenter(this, it) }?.init()
        presenter = localRepository?.let { FavoritePresenter(this, it) }!!

        val layoutManager = LinearLayoutManager(context)
        favoriteList.layoutManager = layoutManager
        favoriteList.setHasFixedSize(true)
        favoriteList.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        movieAdapter = MovieAdapter {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", it.movieId)
            intent.putExtra("ogName", it.movieOriginalName)
            intent.putExtra("name", it.movieName)
            intent.putExtra("release", it.movieRelease)
            intent.putExtra("img", it.movieImg)
            intent.putExtra("ogName", it.movieOriginalName)
            intent.putExtra("score", it.movieScore)
            intent.putExtra("desc", it.movieDesc)
            startActivity(intent)
        }
        favoriteList.adapter = movieAdapter
    }

    override fun showFavorites(movieExample: List<MovieExample>) {
        movieAdapter.addMovies(movieExample)
    }

    override fun deleteAll() {
        Toast.makeText(context, "All Favorites Deleted", Toast.LENGTH_SHORT).show()
        presenter.deleteFavorites()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
