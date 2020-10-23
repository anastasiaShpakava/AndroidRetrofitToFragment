package com.company.androidretrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NameFragment extends Fragment {
    private List<Source> data;
    private RecyclerView recyclerView;
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "39f328d281294c998df37ec5b9d04305";
    private ApiInterface apiInterface;
    private NewsAdapter newsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_fragment, container, false);
        loadJSON();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;

    }

    private void loadJSON() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        final Call<News> news = apiInterface.getNews(API_KEY);
        news.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                List<Source> sources = response.body().getSources();
                newsAdapter = new NewsAdapter(getActivity(), response.body().getSources());
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
