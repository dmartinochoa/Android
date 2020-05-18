package net.main.ui.activities.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.main.data.model.MovieExample
import net.main.data.network.ApiInterface
import net.main.data.network.MovieResult
import net.main.ui.activities.detail.DetailActivity
import net.main.ui.adaptor.MovieAdapter
import net.test.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val movieList = arrayListOf<MovieExample>()
        val searchName = intent.getStringExtra("movieName")

        val layoutManager = LinearLayoutManager(this)
        val searchResults = findViewById<RecyclerView>(R.id.searchResults)
        searchResults.layoutManager = layoutManager
        searchResults.setHasFixedSize(true)
        searchResults.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )

        movieAdapter = MovieAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it.movieId)
            intent.putExtra("name", it.movieName)
            intent.putExtra("release", it.movieRelease)
            intent.putExtra("img", it.movieImg)
            intent.putExtra("ogName", it.movieOriginalName)
            intent.putExtra("score", it.movieScore)
            intent.putExtra("desc", it.movieDesc)
            startActivity(intent)
        }

        searchResults.adapter = movieAdapter


        val retrofit = Retrofit.Builder().baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.searchMovies(
            api_key, searchName,
            language
        )?.enqueue(object : Callback<MovieResult?> {
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
                        movieList.add(movieElement)
                    }
                    movieAdapter.addMovies(movieList)
                }
            }

            override fun onFailure(
                call: Call<MovieResult?>,
                t: Throwable
            ) {
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
