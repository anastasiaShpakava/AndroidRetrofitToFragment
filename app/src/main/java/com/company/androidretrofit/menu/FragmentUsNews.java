package com.company.androidretrofit.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.androidretrofit.R;
import com.company.androidretrofit.model.News;
import com.company.androidretrofit.model.Source;
import com.company.androidretrofit.restApi.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUsNews extends Fragment implements Callback<News> {
    private RecyclerView recyclerView;
    private static final String API_KEY = "39f328d281294c998df37ec5b9d04305";
    private static final String US_NEWS_CATEGORY = "us";
    private AdapterSortedByCountry adapterSortedByCountry;
    private RestClient restClient = new RestClient();
    private List<Source> sources;

    public FragmentUsNews() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_us, container, false);
        restClient.startRetrofit();
        loadJSON();
        recyclerView = view.findViewById(R.id.recyclerViewUs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }
    private void loadJSON() {
        final Call<News> newsByCountry = restClient.getApiInterface().getByCountry(API_KEY,US_NEWS_CATEGORY);
        newsByCountry.enqueue(this);
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        sources = response.body().getSources();
        adapterSortedByCountry = new AdapterSortedByCountry(getActivity(), sources);
        recyclerView.setAdapter(adapterSortedByCountry);
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d("error", "can't parse data: ", t);
    }
}
