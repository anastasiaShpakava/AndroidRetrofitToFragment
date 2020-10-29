package com.company.androidretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new NameFragment())
                    .commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    NameFragment nameFragment = new NameFragment();
                    switch (item.getItemId()){
                        case R.id.english_news:
                            nameFragment.englishNews();
                            break;

                        case R.id.us_news:
                            nameFragment.usNews();
                            break;
                    }
                    return false;
                }
            };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
    }
}
