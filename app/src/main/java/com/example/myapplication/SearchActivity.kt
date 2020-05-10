package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    public var movieList = arrayListOf<MovieExample>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val intent = getIntent()
        val searchName = intent.getStringExtra("movieName")
        val retrofit = Retrofit.Builder().baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val interfce = retrofit.create(ApiInterface::class.java)
        Log.d("test", searchName)
        val call = interfce.searchMovies(api_key, language, searchName.toString(), page)
        val test = this
        if (call != null) {
            call.enqueue(object : Callback<MovieResult?> {
                override fun onResponse(
                    call: Call<MovieResult?>,
                    response: Response<MovieResult?>
                ) {
                    val res = response.body()
                    val movies =
                        res?.results
                    if (movies != null) {
                        for (element in movies) {
                            val movie = element
                            val movieElement = MovieExample(
                                movie.posterPath,
                                movie.title,
                                movie.voteAverage,
                                movie.overview,
                                movie.releaseDate
                            )
                            movieList.add(
                                movieElement
                            )
                        }
                    }
                    val adapter = MovieAdapter(movieList)
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.setHasFixedSize(true)
                    val layoutManager = LinearLayoutManager(test)
                    recyclerView.layoutManager = layoutManager
                    recyclerView.adapter = adapter
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

    }

    companion object {
        var base = "https://api.themoviedb.org"
        var page = 1
        var api_key = "ac99f5d02a905e942cd90754fba21c3a"
        var language = "en-US"
    }
}
