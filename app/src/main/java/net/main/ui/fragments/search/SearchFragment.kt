package net.main.ui.fragments.search


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_favorites.*
import net.main.data.model.MovieExample
import net.main.data.network.ApiInterface
import net.main.data.network.MovieResult
import net.main.ui.activities.detail.DetailActivity
import net.main.ui.activities.search.SearchActivity
import net.main.ui.adaptor.MovieAdapter
import net.test.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchButton = view.findViewById<Button>(R.id.searchButton)
        searchButton.isEnabled = false
        val searchText = view.findViewById<EditText>(R.id.movieSearch)
        searchButton.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra("movieName", searchText.text.toString())
            this.startActivity(intent)
        }
        searchText.addTextChangedListener(){
            searchButton.isEnabled = searchText.text.isNotEmpty()

        }

        val movieList = arrayListOf<MovieExample>()

        val layoutManager = LinearLayoutManager(context)
        val popularList = view.findViewById<RecyclerView>(R.id.popularList)
        popularList.layoutManager = layoutManager
        popularList.setHasFixedSize(true)
        popularList.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )
        movieAdapter = MovieAdapter {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", it.movieId)
            startActivity(intent)
        }
        popularList.adapter = movieAdapter


        val retrofit = Retrofit.Builder().baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getMovies("popular", api_key, language)
            ?.enqueue(object : Callback<MovieResult?> {
                override fun onResponse(
                    call: Call<MovieResult?>,
                    response: Response<MovieResult?>
                ) {
                    val res = response.body()
                    val movies =
                        res?.results
                    if (movies != null) {

                        for (element in movies) {
                            val movieElement = MovieExample(
                                element.id,
                                element.posterPath,
                                element.title,
                                element.voteAverage,
                                element.originalTitle,
                                element.releaseDate,
                                element.overview
                            )
                            movieList.add(
                                movieElement
                            )
                        }
                        movieAdapter.addMovies(movieList)
                    }
                }

                override fun onFailure(
                    call: Call<MovieResult?>,
                    t: Throwable
                ) {
                    Log.d("test", "fail?")
                    t.printStackTrace()
                }
            })
    }

    companion object {
        val base = "https://api.themoviedb.org"
        val api_key = "ac99f5d02a905e942cd90754fba21c3a"
        val language = "en-US"
    }
}
