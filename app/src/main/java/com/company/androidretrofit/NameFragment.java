package com.company.androidretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NameFragment extends Fragment implements OnTextClickListener, Callback<News> {
    private RecyclerView recyclerView;
    private static final String API_KEY = "39f328d281294c998df37ec5b9d04305";
    private NewsAdapter newsAdapter;
    private RestClient restClient = new RestClient();

    public NameFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.name_fragment, container, false);
        restClient.startRetrofit();
        loadJSON();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;

    }

    private void loadJSON() {
        final Call<News> news = restClient.getApiInterface().getNews(API_KEY);
        news.enqueue(this);
    }

    @Override
    public void onTextClick(String name, String description, String url, String category, String language, String country) {

        AppCompatActivity activity = (AppCompatActivity) getContext();
        Bundle bundle = new Bundle();
        bundle.putString("sourceName", name);
        bundle.putString("sourceDescription", description);
        bundle.putString("sourceUrl", url);
        bundle.putString("sourceCategory", category);
        bundle.putString("sourceLanguage", language);
        bundle.putString("sourceCountry", country);

        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(bundle);

        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newsFragment).addToBackStack(null).commit();
        Toast.makeText(getActivity(), "Get info", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        List<Source> sources = response.body().getSources();

        newsAdapter = new NewsAdapter(getActivity(), sources, this);
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d("error", "can't parse data: ", t);
    }
}
