package com.example.newsdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;

import com.example.newsdroid.parameter.Articles;
import com.example.newsdroid.parameter.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    final String API_KEY = "YOUR_API_KEY";
    Button refreshButton;
    List<Articles> articles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        refreshButton = findViewById(R.id.refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country = getCountry();
        fetchJSON(country, API_KEY);
    }

    private void fetchJSON(String country, String api_key) {
        Call<Headlines> call = Client.getInstance().getAPI().getHeadlines(country, API_KEY);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null)
                {
                    articles.clear();
                    articles = response.body().getArticles();
                    

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

            }
        });
    }

    private String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
