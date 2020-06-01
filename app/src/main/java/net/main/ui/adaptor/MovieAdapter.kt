package net.main.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import net.main.data.model.MovieExample
import net.test.R

class MovieAdapter(
    private val listener: (movie: MovieExample) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movies = listOf<MovieExample>()

    fun addMovies(movies: List<MovieExample>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_example, parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        Picasso.get().load(currentMovie.movieImg)
            .into(holder.imageView)
        holder.movieName.text = currentMovie.movieName
        holder.movieScore.text = currentMovie.movieScore
        holder.movieOriginalName.text = currentMovie.movieOriginalName
        holder.releaseDateValue.text = currentMovie.movieRelease
        holder.bind(movies[position], listener)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById<ImageView>(R.id.movieImage)
        var movieName: TextView = itemView.findViewById<TextView>(R.id.movieName)
        var movieScore: TextView = itemView.findViewById<TextView>(R.id.movieScore)
        var movieOriginalName: TextView = itemView.findViewById<TextView>(R.id.movieOriginalName)
        var releaseDateValue: TextView = itemView.findViewById<TextView>(R.id.releaseDateValue)
        private var item: RelativeLayout = itemView.findViewById<RelativeLayout>(R.id.movieEntry)

        fun bind(movie: MovieExample, listener: (movie: MovieExample) -> Unit) {
            movieName.text = movie.movieName
            item.setOnClickListener {
                listener(movie)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.movie_example, parent, false)
            return MovieViewHolder(v)
        }
    }

}