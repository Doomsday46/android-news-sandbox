package com.sandbox.android_news_sandbox.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.model.News;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm";
    private List<News> listNews;
    private Set<String> uniqueTitles;
    private SimpleDateFormat simpleDateFormat;

    public NewsAdapter() {
        this.listNews = new ArrayList<>();
        this.uniqueTitles = new HashSet<>();
        simpleDateFormat = new SimpleDateFormat(PATTERN_DATE_TIME);
    }

    public List<News> getListNews() {
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        addListNews(listNews);
    }

    private void updateUniqueTitles(List<News> listNews){
        uniqueTitles.addAll(Observable.fromIterable(listNews).map(News::getTitle).distinct().toList().blockingGet());
    }

    public void addListNews(List<News> listNews) {
        Observable<News> filter = Observable.fromIterable(listNews).filter(a -> !uniqueTitles.contains(a.getTitle())).doAfterNext(a -> uniqueTitles.add(a.getTitle()));
        this.listNews.addAll(filter.toList().blockingGet());
        notifyDataSetChanged();
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
        TextView contentView = cardView.findViewById(R.id.content);
        TextView author = cardView.findViewById(R.id.author);
        TextView date = cardView.findViewById(R.id.date);
        TextView description = cardView.findViewById(R.id.description);

        setData(titleView, contentView, imageView, position, author, date, description);
    }

    @Override
    public int getItemCount(){
        return listNews.size();
    }

    private void setData(TextView title, TextView content, ImageView image, int position, TextView author, TextView date, TextView description){
        News news = getData(position);

        title.setText(news.getTitle());
        content.setText(news.getContent());
        Picasso.get().load(news.getUrlImage()).fit().centerInside().into(image);
        image.setContentDescription("mock");
        if (news.getAuthor() == null || news.getAuthor().isEmpty()) author.setVisibility(View.GONE);
        else {
            author.setText(news.getAuthor());
            author.setVisibility(View.VISIBLE);
        }
        date.setText(simpleDateFormat.format(news.getDate()));
        description.setText(news.getDescription());
    }

    private News getData(int position){
        return listNews.get(position);
    }
}
