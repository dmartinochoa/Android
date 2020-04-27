package net.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.test.R


class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = getView()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        var layoutManager = recyclerView?.layoutManager
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
        val adapter = MovieAdapter(movieList)
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true)
        }
        layoutManager = LinearLayoutManager(context)
        if (recyclerView != null) {
            recyclerView.layoutManager = layoutManager
        }
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }
    }

}
