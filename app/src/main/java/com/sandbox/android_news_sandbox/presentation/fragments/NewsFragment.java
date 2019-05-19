package com.sandbox.android_news_sandbox.presentation.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandbox.android_news_sandbox.R;
import com.sandbox.android_news_sandbox.data.NewsRepositoryImpl;
import com.sandbox.android_news_sandbox.model.News;
import com.sandbox.android_news_sandbox.model.NewsInteractorImpl;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;
import com.sandbox.android_news_sandbox.presentation.NewsAdapter;
import com.sandbox.android_news_sandbox.presentation.NewsPresenter;
import com.sandbox.android_news_sandbox.presentation.Presenter;
import com.sandbox.android_news_sandbox.presentation.view.NewsView;

import java.util.List;


public class NewsFragment extends Fragment implements NewsView {

    private Presenter presenter;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private CategoryNews categoryNews;
    private View progressBar;
    private OnRefreshFinishedListener onRefreshFinishedListener;


    public NewsFragment() {
        super();
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView sportsRecyclerView = (RecyclerView)inflater.inflate(
                R.layout.news_fragment, container, false);

        if (savedInstanceState != null && savedInstanceState.containsKey("category")) {
            categoryNews = CategoryNews.values()[savedInstanceState.getInt("category")];
        }

        progressBar = getActivity().findViewById(R.id.rect_progress_bar);
        recyclerView = sportsRecyclerView.findViewById(R.id.news_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(sportsRecyclerView.getContext()));
        newsAdapter = new NewsAdapter();

        presenter = new NewsPresenter(new NewsInteractorImpl(new NewsRepositoryImpl(), categoryNews), this);


        recyclerView.setAdapter(newsAdapter);
        recyclerView.setVisibility(View.GONE);

        presenter.loadData();
        return sportsRecyclerView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("category", categoryNews.ordinal());

    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNews(List<News> news) {
        newsAdapter.setListNews(news);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (onRefreshFinishedListener != null) onRefreshFinishedListener.onRefreshFinished();
    }

    @Override
    public void update() {
        presenter.loadData();
    }

    public void setOnRefreshFinishedListener(OnRefreshFinishedListener listener) {
        this.onRefreshFinishedListener = listener;
    }

    public void setCategoryNews(CategoryNews categoryNews) {
        this.categoryNews = categoryNews;
    }

    public CategoryNews getCategoryNews() {
        return categoryNews;
    }

    public interface OnRefreshFinishedListener {
       void onRefreshFinished();
    }


}
