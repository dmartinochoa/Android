package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.MovieAdapter.movieViewHolder
import com.squareup.picasso.Picasso
import java.util.*

class MovieAdapter(private val exampleList: ArrayList<MovieExample>) :
    RecyclerView.Adapter<movieViewHolder>() {

    class movieViewHolder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView
        var movieName: TextView
        var movieScore: TextView
        var movieOriginalName: TextView
        var releaseDateValue: TextView

        init {
            imageView = itemView.findViewById(R.id.movieImage)
            movieName = itemView.findViewById(R.id.movieTitle)
            movieScore = itemView.findViewById(R.id.movieScore)
            movieOriginalName = itemView.findViewById(R.id.movieDirector)
            releaseDateValue = itemView.findViewById(R.id.movieDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return movieViewHolder(v)
    }

    override fun onBindViewHolder(holder: movieViewHolder, position: Int) {
        val currentMovie = exampleList[position]
        Picasso.get().load("http://image.tmdb.org/t/p/w500" + currentMovie.imageResouse)
            .into(holder.imageView)
        holder.movieName.text = currentMovie.movieName
        holder.movieScore.text = currentMovie.movieScore
        holder.movieOriginalName.text = currentMovie.movieDirector
        holder.releaseDateValue.text = currentMovie.releaseDateValue
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

}