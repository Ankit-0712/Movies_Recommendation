package com.example.moviesrecommendation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("/predict")
    Call<List<String>> getRecommendations(@Query("movie_name") String movieName);
}