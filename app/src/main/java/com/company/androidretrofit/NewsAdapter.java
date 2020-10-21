package com.company.androidretrofit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private LayoutInflater inflater;
    private List<Source> sources;
   static NameFragment nameFragment = new NameFragment();

    public NewsAdapter(List<Source> sources) {
        this.sources = sources;
    }

    public NewsAdapter(Context context, List<Source> sources) {
        this.sources = sources;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {
        final Source source = sources.get(position);
        holder.sourceDescription.setText(source.getDescription());
        holder.sourceURL.setText(source.getUrl());
        holder.sourceCategory.setText(source.getCategory());
        holder.sourceLanguage.setText(source.getLanguage());
        holder.sourceCountry.setText(source.getCountry());
        holder.sourceName.setText(source.getName());
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView sourceName, sourceId, sourceDescription, sourceURL, sourceCategory, sourceLanguage, sourceCountry;

        public NewsViewHolder(View view) {
            super(view);
            sourceName = view.findViewById(R.id.sourceName);
            sourceId = view.findViewById(R.id.sourceId);
            sourceDescription = view.findViewById(R.id.sourceDescription);
            sourceURL = view.findViewById(R.id.sourceURL);
            sourceCategory = view.findViewById(R.id.sourceCategory);
            sourceLanguage = view.findViewById(R.id.sourceLanguage);
            sourceCountry = view.findViewById(R.id.sourceCountry);


        }
    }
}
