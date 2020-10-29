package com.company.androidretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
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
    private List<Source> sources;

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
    public void onTextClick(Source source) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("sourceData", source);

        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newsFragment).addToBackStack(null).commit();
        Toast.makeText(getActivity(), "Get info", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        sources = response.body().getSources();
        newsAdapter = new NewsAdapter(getActivity(), sources, this);
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d("error", "can't parse data: ", t);
    }
protected void englishNews(){
        Toast.makeText(getActivity(),"English news", Toast.LENGTH_LONG).show();
}
    protected void usNews(){
        Toast.makeText(getActivity(),"USA news", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        return super.onOptionsItemSelected(item);
    }
}