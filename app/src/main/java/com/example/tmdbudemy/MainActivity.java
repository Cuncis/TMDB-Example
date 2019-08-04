package com.example.tmdbudemy;

import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.tmdbudemy.adapter.MovieAdapter;
import com.example.tmdbudemy.api.ApiClient;
import com.example.tmdbudemy.api.MovieDBService;
import com.example.tmdbudemy.model.Movie;
import com.example.tmdbudemy.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Movie> movieList;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TMDB Popular Movies Today");
        swipeRefreshLayout = findViewById(R.id.swipe);

        movieList = new ArrayList<>();

        getPopularMovies();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    private void showRecyclerview() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MovieAdapter(this, movieList);

        recyclerView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void getPopularMovies() {
        MovieDBService movieDBService = ApiClient.getMovieDBService();
        Call<MovieResponse> call = movieDBService.getPopularMovies(getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                swipeRefreshLayout.setRefreshing(false);
                MovieResponse result = response.body();
                if (response.isSuccessful()) {
                    Log.d("_logMovie", "onResponse: " + response.body().getmovies().size());
                    movieList = result.getmovies();
                    showRecyclerview();
                } else {
                    Toast.makeText(MainActivity.this, "Error1: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Error2: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
