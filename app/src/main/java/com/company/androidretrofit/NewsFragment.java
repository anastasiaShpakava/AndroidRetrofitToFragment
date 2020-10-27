package com.company.androidretrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class NewsFragment extends Fragment {
    private String sourceName;
    private String sourceDescription;
    private String sourceUrl;
    private String sourceCategory;
    private String sourceLanguage;
    private String sourceCountry;

    public NewsFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        TextView textName = view.findViewById(R.id.sourceName);
        TextView textUrl = view.findViewById(R.id.sourceURL);
        TextView textDescription = view.findViewById(R.id.sourceDescription);
        TextView textCategory = view.findViewById(R.id.sourceCategory);
        TextView textLanguage = view.findViewById(R.id.sourceLanguage);
        TextView textCountry = view.findViewById(R.id.sourceCountry);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            sourceName = bundle.getString("sourceName");
            sourceUrl = bundle.getString("sourceUrl");
            sourceDescription = bundle.getString("sourceDescription");
            sourceCategory = bundle.getString("sourceCategory");
            sourceLanguage = bundle.getString("sourceLanguage");
            sourceCountry = bundle.getString("sourceCountry");

            textName.setText(sourceName);
            textUrl.setText(sourceUrl);
            textDescription.setText(sourceDescription);
            textCategory.setText(sourceCategory);
            textLanguage.setText(sourceLanguage);
            textCountry.setText(sourceCountry);
        }
        return view;
    }
}
