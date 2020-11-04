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
import android.widget.LinearLayout;
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
    private WebView webView;
    private LinearLayout fragmentLayout;

private Source source;
    public NewsFragment() {
    }

    public NewsFragment(Source source) {
        this.source = source;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        webView = view.findViewById(R.id.webview);

        fragmentLayout = view.findViewById(R.id.fragment_layout);
        textName = view.findViewById(R.id.sourceName);
        textUrl = view.findViewById(R.id.sourceURL);
        textDescription = view.findViewById(R.id.sourceDescription);
        textCategory = view.findViewById(R.id.sourceCategory);
        textLanguage = view.findViewById(R.id.sourceLanguage);
        textCountry = view.findViewById(R.id.sourceCountry);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bundle.putSerializable("sourceData", source);

            textName.setText(source.getName());
            textUrl.setText(source.getUrl());
            textDescription.setText(source.getDescription());
            textCategory.setText(source.getCategory());
            textLanguage.setText(source.getLanguage());
            textCountry.setText(source.getCountry());

            textUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    webView.loadUrl(source.getUrl());
                    // Enable Javascript
                    webView.getSettings().setJavaScriptEnabled(true);
                    // Force links and redirects to open in the WebView instead of in a browser
                    webView.setWebViewClient(new WebViewClient());
                    fragmentLayout.setVisibility(View.GONE);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.night:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            case R.id.day:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.zoomIn:
                textName.setTextAppearance(getContext(), R.style.boldText);
                textCategory.setTextAppearance(getContext(), R.style.boldText);
                textCountry.setTextAppearance(getContext(), R.style.boldText);
                textDescription.setTextAppearance(getContext(), R.style.boldText);
                textLanguage.setTextAppearance(getContext(), R.style.boldText);
                textUrl.setTextAppearance(getContext(), R.style.boldText);
            case R.id.zoomOut:
                textName.setTextAppearance(getContext(), R.style.normalText);
                textCategory.setTextAppearance(getContext(), R.style.normalText);
                textCountry.setTextAppearance(getContext(), R.style.normalText);
                textDescription.setTextAppearance(getContext(), R.style.normalText);
                textLanguage.setTextAppearance(getContext(), R.style.normalText);
                textUrl.setTextAppearance(getContext(), R.style.normalText);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
