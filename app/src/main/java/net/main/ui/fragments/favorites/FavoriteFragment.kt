package net.main.ui.fragments.favorites

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import net.main.data.model.MovieExample
import net.main.data.repository.local.MovieDatabaseFactory
import net.main.data.repository.remote.RoomLocalRepository
import net.main.ui.activities.detail.DetailActivity
import net.main.ui.activities.search.SearchActivity
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
        presenter.init()
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
        favSearchButton.isEnabled = false

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
            startActivity(intent)
        }
        favoriteList.adapter = movieAdapter

        favSearchButton.setOnClickListener() {
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra("movieName", favSearchText.text.toString())
            this.startActivity(intent)
        }
        favSearchText.addTextChangedListener() {
            favSearchButton.isEnabled = favSearchText.text.isNotEmpty()
        }
    }

    override fun showFavorites(movieExample: List<MovieExample>) {
        movieAdapter.addMovies(movieExample)
        if (movieExample.isEmpty()) {
            favSearchButton.visibility = View.VISIBLE
            favSearchText.visibility = View.VISIBLE
            Toast.makeText(context, "No favorites added", Toast.LENGTH_SHORT).show()
        } else {
            favSearchButton.visibility = View.GONE
            favSearchText.visibility = View.GONE
        }

    }

    override fun deleteAll() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to delete all favorites?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                Toast.makeText(context, "All Favorites Deleted", Toast.LENGTH_SHORT).show()
                presenter.deleteFavorites()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
