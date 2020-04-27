package net.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.test.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.movieViewHolder> {
    private ArrayList<MovieExample> exampleList;

    static class movieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView movieName;
        TextView movieScore;
        TextView movieOriginalName;
        TextView releaseDateValue;

        movieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewProfile);
            movieName = itemView.findViewById(R.id.movieName);
            movieScore = itemView.findViewById(R.id.movieScore);
            movieOriginalName = itemView.findViewById(R.id.movieOriginalName);
            releaseDateValue = itemView.findViewById(R.id.releaseDateValue);
        }
    }

    public MovieAdapter(ArrayList<MovieExample> exampleList) {
        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_example, parent, false);
        return new movieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        MovieExample currentMovie = exampleList.get(position);
        holder.imageView.setImageResource(currentMovie.getImageResouse());
        holder.movieName.setText(currentMovie.getMovieName());
        holder.movieScore.setText(currentMovie.getMovieScore());
        holder.movieOriginalName.setText(currentMovie.getMovieOriginalName());
        holder.releaseDateValue.setText(currentMovie.getReleaseDateValue());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }
}
