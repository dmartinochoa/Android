package net.main.ui.activities.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.main.data.model.MovieExample
import net.main.data.repository.local.MovieDatabaseFactory
import net.main.data.repository.remote.RoomLocalRepository
import net.test.R


class DetailActivity : AppCompatActivity(), DetailPresenter.View {
    private lateinit var presenter: DetailPresenter
    private lateinit var localRepository: RoomLocalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getLongExtra("id", 1)
        val name = intent.getStringExtra("name")
        val originalName = intent.getStringExtra("ogName")
        val releaseDate = intent.getStringExtra("release")
        val img = intent.getStringExtra("img")
        val score = intent.getStringExtra("score")
        val desc = intent.getStringExtra("desc")
        detailNameValue.text = name
        detailReleaseDateValue.text = releaseDate
        Picasso.get().load(img).into(detailImg)
        detailDescValue.text = desc

        localRepository = RoomLocalRepository(MovieDatabaseFactory.get(this))
        presenter = DetailPresenter(this, localRepository)
        presenter.init(id)

        star.setOnClickListener {
            val movieExample = MovieExample(id, img, name, score, originalName, releaseDate, desc)
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
