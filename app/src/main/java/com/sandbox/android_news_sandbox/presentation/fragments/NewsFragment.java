package com.sandbox.android_news_sandbox.presentation.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.data.NewsRepositoryImpl;
import com.sandbox.android_news_sandbox.model.NewsInteractorImpl;
import com.sandbox.android_news_sandbox.model.News;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;
import com.sandbox.android_news_sandbox.presentation.NewsAdapter;
import com.sandbox.android_news_sandbox.presentation.NewsPresenter;
import com.sandbox.android_news_sandbox.presentation.Presenter;
import com.sandbox.android_news_sandbox.presentation.view.NewsView;

import java.io.Serializable;
import java.nio.BufferUnderflowException;
import java.util.List;


public class NewsFragment extends Fragment implements NewsView {

    private Presenter presenter;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private CategoryNews categoryNews;
    private ProgressBar progressBar;


    public NewsFragment() {
        super();
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView sportsRecyclerView = (RecyclerView)inflater.inflate(
                R.layout.news_fragment, container, false);

        recyclerView = sportsRecyclerView.findViewById(R.id.news_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(sportsRecyclerView.getContext()));
        newsAdapter = new NewsAdapter();

        presenter = new NewsPresenter(new NewsInteractorImpl(new NewsRepositoryImpl(), categoryNews), this);


        recyclerView.setAdapter(newsAdapter);
        recyclerView.setVisibility(View.GONE);

        presenter.loadData();
        return sportsRecyclerView;
    }

    public void setProgressBar(ProgressBar progressBar){
        this.progressBar = progressBar;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("category",categoryNews.toString());

    }



    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNews(List<News> news) {
        newsAdapter.setListNews(news);
        newsAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void update() {
        presenter.loadData();
    }

    public void setCategoryNews(CategoryNews categoryNews) {
        this.categoryNews = categoryNews;
    }

    public CategoryNews getCategoryNews() {
        return categoryNews;
    }


}
