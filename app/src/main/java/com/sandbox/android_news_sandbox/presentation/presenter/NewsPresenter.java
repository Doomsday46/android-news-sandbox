package com.sandbox.android_news_sandbox.presentation.presenter;

import android.annotation.SuppressLint;

import com.sandbox.android_news_sandbox.model.News;
import com.sandbox.android_news_sandbox.model.NewsInteractor;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;
import com.sandbox.android_news_sandbox.presentation.view.NewsView;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter implements Presenter {

    private NewsView view;
    private final NewsInteractor newsInteractor;

    public NewsPresenter(NewsInteractor newsInteractor) {
        this.newsInteractor = newsInteractor;
    }


    @Override
    public void init(CategoryNews categoryNews, NewsView newsView) {
        this.view = newsView;
        newsInteractor.setCategoryNews(categoryNews);
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadData() {
        newsInteractor.getNews().subscribe(
                view::setNews,
                throwable -> { view.setNews(Collections.emptyList()); });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveNews(List<News> newsList) {
        Observable.just(newsList).subscribe(newsInteractor::saveData);
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadDataFromDatabase() {
        newsInteractor.getNewsFromDatabase().subscribe(
                view::setNews,
                throwable ->{ view.setNews(Collections.emptyList()); });
    }

}
