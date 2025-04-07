package com.example.moviesrecommendation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etMovieName;
    private Button btnRecommend;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovieName = findViewById(R.id.etMovieName);
        btnRecommend = findViewById(R.id.btnRecommend);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieName = etMovieName.getText().toString().trim();
                if (movieName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a movie name", Toast.LENGTH_SHORT).show();
                    return;
                }
                fetchRecommendations(movieName);
            }
        });
    }

    private void fetchRecommendations(String movieName) {
        MovieApiService apiService = Retro.getApiService();
        apiService.getRecommendations(movieName).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> movies = response.body();
                    adapter = new MoviesAdapter(movies);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "No recommendations found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}