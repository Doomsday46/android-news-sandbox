package com.sandbox.android_news_sandbox.presentation;

import android.annotation.SuppressLint;

import com.sandbox.android_news_sandbox.model.NewsInteractor;
import com.sandbox.android_news_sandbox.presentation.view.NewsView;

import java.util.Collections;

public class NewsPresenter implements Presenter {

    private final NewsView view;
    private final NewsInteractor newsInteractor;

    public NewsPresenter(NewsInteractor newsInteractor, NewsView view) {
        this.newsInteractor = newsInteractor;
        this.view = view;
    
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {

        newsInteractor.getNews().subscribe(
                news -> view.setNews(news),
                throwable -> { view.setNews(Collections.emptyList()); throw new NullPointerException(); });
    }




}
