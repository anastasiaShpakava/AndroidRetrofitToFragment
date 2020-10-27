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
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
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
        setHasOptionsMenu(true);
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

            textUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(sourceUrl);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
//        if (item.getItemId() == R.id.zoomIn) {
//            container.size
//        } else {
//            textView.setTextSize(20);
//        }
        return super.onOptionsItemSelected(item);
    }
}
