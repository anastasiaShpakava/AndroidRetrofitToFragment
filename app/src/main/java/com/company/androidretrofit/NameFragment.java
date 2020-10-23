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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NameFragment extends Fragment implements Callback<Source> {
    private List<Source> sources = new ArrayList<>();
    private RecyclerView recyclerView;
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "39f328d281294c998df37ec5b9d04305";
    private ApiInterface apiInterface;
NewsAdapter newsAdapter;
    //private MainActivity mainActivity;
    ArrayList<String> sourcesName = null ;
        @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

private void loadJSON(){
    Call<Source> news = apiInterface.getNews(API_KEY);
    news.enqueue(this);
}
}
