package com.sandbox.android_news_sandbox.presentation.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandbox.android_news_sandbox.App;
import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.model.News;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;
import com.sandbox.android_news_sandbox.presentation.adapter.NewsAdapter;
import com.sandbox.android_news_sandbox.presentation.presenter.Presenter;
import com.sandbox.android_news_sandbox.presentation.view.NewsView;

import java.util.List;

import javax.inject.Inject;

public class TopFragment extends android.app.Fragment implements NewsView {

    @Inject
    Presenter presenter;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    private CategoryNews categoryNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView topView = (RecyclerView)inflater.inflate(
                R.layout.top_view, container, false);


        recyclerView = topView.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(topView.getContext()));
        newsAdapter = new NewsAdapter();


        App.getMainComponent().inject(this);

        presenter.init(categoryNews, this);

        recyclerView.setAdapter(newsAdapter);

        presenter.loadDataFromDatabase();
        return topView;
    }

    @Override
    public void setNews(List<News> news) {

        newsAdapter.setListNews(news);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void update() {
        presenter.loadDataFromDatabase();
    }

    public void setCategoryNews(CategoryNews categoryNews) {
        this.categoryNews = categoryNews;
    }
}
