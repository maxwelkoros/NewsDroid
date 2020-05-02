package com.example.newsdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsdroid.parameter.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Articles> articles;

    public Adapter(Context context, List<Articles> articles){

        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Articles art = articles.get(position);
        String url = art.getUrl();
        holder.newsTitle.setText(art.getTitle());
        holder.newsDate.setText(art.getPublishedAt());
        String imageUrl = art.getUrlToImage();
        Picasso.get().load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }

    public String getCountry(){

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
