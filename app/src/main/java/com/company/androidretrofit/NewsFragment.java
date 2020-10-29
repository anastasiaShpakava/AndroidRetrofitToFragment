package com.company.androidretrofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class NewsFragment extends Fragment {
    private TextView textName;
    private TextView textUrl;
    private TextView textDescription;
    private TextView textCategory;
    private TextView textLanguage;
    private TextView textCountry;

    public NewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);

        textName = view.findViewById(R.id.sourceName);
        textUrl = view.findViewById(R.id.sourceURL);
        textDescription = view.findViewById(R.id.sourceDescription);
        textCategory = view.findViewById(R.id.sourceCategory);
        textLanguage = view.findViewById(R.id.sourceLanguage);
        textCountry = view.findViewById(R.id.sourceCountry);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final Source source = (Source) bundle.getSerializable("sourceData");

            textName.setText(source.getName());
            textUrl.setText(source.getUrl());
            textDescription.setText(source.getDescription());
            textCategory.setText(source.getCategory());
            textLanguage.setText(source.getLanguage());
            textCountry.setText(source.getCountry());

            textUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(source.getUrl());
                    intent.setData(uri);
                    startActivity(intent);
                }
            });
        }
        return view;
    }
}

