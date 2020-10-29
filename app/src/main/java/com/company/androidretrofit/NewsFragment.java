package com.company.androidretrofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
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
        setHasOptionsMenu(true);
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
}
