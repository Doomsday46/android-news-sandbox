package com.sandbox.android_news_sandbox.model;

import com.sandbox.android_news_sandbox.data.NewsRepositoryImpl;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements NewsInteractor {


    private final NewsRepositoryImpl newsRepository;
    private final String categoryNews;

    public NewsInteractorImpl(NewsRepositoryImpl newsRepository, CategoryNews categoryNews) {
        this.newsRepository = newsRepository;
        this.categoryNews = categoryNews.toString();
    }

    @Override
    public Observable<List<News>> getNews() {
        if (categoryNews.equals("undefined")) return newsRepository.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return newsRepository.getNews(categoryNews)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
