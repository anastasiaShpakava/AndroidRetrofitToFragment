package com.company.androidretrofit.restApi;


import com.company.androidretrofit.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("sources")
    Call<News> getNews(
            @Query("apiKey") String apiKey
    );
}
