package com.company.androidretrofit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private LayoutInflater inflater;
    private List<Source> sources;
    private OnTextClickListener listener;

    public NewsAdapter(Context context, List<Source> sources, OnTextClickListener listener) {
        this.sources = sources;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
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
        holder.sourceId.setText(source.getId());

        holder.sourceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTextClick(source.getName(), source.getDescription(), source.getUrl(), source.getCategory(),source.getLanguage(),source.getCountry());
            }
        });

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
            sourceId = view.findViewById(R.id.sourceIdItem);
            sourceDescription = view.findViewById(R.id.sourceDescription);
            sourceURL = view.findViewById(R.id.sourceURL);
            sourceCategory = view.findViewById(R.id.sourceCategory);
            sourceLanguage = view.findViewById(R.id.sourceLanguage);
            sourceCountry = view.findViewById(R.id.sourceCountry);

        }
    }
}
