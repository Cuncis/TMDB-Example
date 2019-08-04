package com.example.tmdbudemy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tmdbudemy.DetailActivity;
import com.example.tmdbudemy.MainActivity;
import com.example.tmdbudemy.R;
import com.example.tmdbudemy.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.tvTitle.setText(movie.getOriginalTitle());
        holder.tvRating.setText(Double.toString(movie.getVoteAverage()));

        String imgPath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        Glide.with(context)
                .load(imgPath)
                .placeholder(R.drawable.loading)
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private TextView tvTitle,tvRating;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        Movie selectedMovie = movieList.get(position);

                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("MOVIE_KEY", selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

}
