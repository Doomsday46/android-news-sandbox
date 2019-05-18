package com.sandbox.android_news_sandbox.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.model.News;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> listNews;

    public NewsAdapter() {
        this.listNews = new ArrayList<>();
    }

    public List<News> getListNews() {
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        this.listNews = listNews;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false);

        return new ViewHolder(cv);
    }



    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position){


        CardView cardView = holder.cardView;

        ImageView imageView = cardView.findViewById(R.id.info_image);
        TextView titleView = cardView.findViewById(R.id.title_news);
        TextView contentView = cardView.findViewById(R.id.info_news);

        setData(titleView, contentView, imageView, position);
    }

    @Override
    public int getItemCount(){
        return listNews.size();
    }

    private void setData(TextView title, TextView content, ImageView image, int position){
        News news = getData(position);

        title.setText(news.getTitle());
        content.setText(news.getContent());
        Picasso.get().load(news.getUrlImage()).into(image);
        image.setContentDescription("mock");
    }

    private News getData(int position){
        return listNews.get(position);
    }
}