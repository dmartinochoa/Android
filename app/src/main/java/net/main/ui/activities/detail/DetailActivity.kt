package net.main.ui.activities.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.main.data.model.MovieExample
import net.main.data.network.ApiInterface
import net.main.data.network.MovieDetailResult
import net.main.data.repository.local.MovieDatabaseFactory
import net.main.data.repository.remote.RoomLocalRepository
import net.main.ui.activities.search.SearchActivity
import net.test.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailActivity : AppCompatActivity(), DetailPresenter.View {
    private lateinit var presenter: DetailPresenter
    private lateinit var localRepository: RoomLocalRepository
    private lateinit var movieExample: MovieExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getLongExtra("id", 1)
        Log.e("test", id.toString())
        localRepository = RoomLocalRepository(MovieDatabaseFactory.get(this))
        presenter = DetailPresenter(this, localRepository)
        presenter.init(id)

        val retrofit = Retrofit.Builder().baseUrl(SearchActivity.base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.searchMovieById(
            id,
            SearchActivity.api_key,
            SearchActivity.language
        )?.enqueue(object : Callback<MovieDetailResult?> {
            override fun onResponse(
                call: Call<MovieDetailResult?>,
                response: Response<MovieDetailResult?>
            ) {
                val res = response.body()
                val movie = res
                if (movie != null) {
                    val name = movie.title
                    val originalName = movie.originalTitle
                    val releaseDate = movie.releaseDate
                    val img = movie.posterPath
                    val score = movie.voteAverage
                    val desc = movie.overview
                    detailNameValue.text = name
                    detailReleaseDateValue.text = releaseDate
                    Picasso.get().load("http://image.tmdb.org/t/p/w500" + img).into(detailImg)
                    detailDescValue.text = desc
                    movieExample =
                        MovieExample(
                            id,
                            img.toString(),
                            name,
                            score.toString(),
                            originalName,
                            releaseDate,
                            desc
                        )
                }
            }

            override fun onFailure(
                call: Call<MovieDetailResult?>,
                t: Throwable
            ) {
                t.printStackTrace()
            }
        })

        star.setOnClickListener {
            presenter.starClicker(star.isChecked, movieExample)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun movieInFav(inFav: Boolean) {
        star.isChecked = inFav
    }
}
