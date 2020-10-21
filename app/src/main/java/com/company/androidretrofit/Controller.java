package com.company.androidretrofit;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<Source> {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "39f328d281294c998df37ec5b9d04305";

    private ApiInterface apiInterface;
    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    List<Source> sources = new ArrayList<>();

    public Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void start() {

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

        Call<Source> news = apiInterface.getNews(API_KEY);
        news.enqueue(this);
    }

    @Override
    public void onResponse(Call<Source> call, Response<Source> response) {
        Source source= response.body();


        String sourceName = source.getName();
        Bundle bundle = new Bundle();
        bundle.putString("sourceName", sourceName);
        FragmentManager fm = mainActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        NameFragment fragmentCurrent = new NameFragment();
        fragmentCurrent.setArguments(bundle);
        ft.add(R.id.fragment, fragmentCurrent);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onFailure(Call<Source> call, Throwable t) {
        Log.d("error", "can't parse data: ", t);
    }
}

