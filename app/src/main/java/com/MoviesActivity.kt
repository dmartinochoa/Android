package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MovieAdapter
import com.MovieExample


class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var adapter = recyclerView.adapter
        var layoutManager = recyclerView.layoutManager
        val movieList = arrayListOf<MovieExample>()
        for (i in 1..50) {
            movieList.add(
                MovieExample(
                    R.drawable.godfather,
                    "El Padrino $i",
                    "9.8",
                    "The GodFather, Part $i",
                    "20-10-1972"
                )
            )
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(movieList)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(adapter)


    }
}
