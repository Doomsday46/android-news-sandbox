package com.sandbox.android_news_sandbox.model;

import android.annotation.SuppressLint;

import com.sandbox.android_news_sandbox.data.database.AppDatabase;
import com.sandbox.android_news_sandbox.data.database.NewsEntity;
import com.sandbox.android_news_sandbox.data.mapper.Mapper;
import com.sandbox.android_news_sandbox.data.network.repository.NewsRepository;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewsInteractorImpl implements NewsInteractor {

    private final NewsRepository newsRepository;
    private String categoryNews;
    private final AppDatabase appDatabase;
    private final Mapper<List<NewsEntity>, List<News>> newsToNewsEntityMapper;
    private final Mapper<List<News>, List<NewsEntity>>  newsEntityToNewsMapper;

    public NewsInteractorImpl(NewsRepository newsRepository, AppDatabase appDatabase, Mapper<List<NewsEntity>,
                              List<News>> newsToNewsEntityMapper, Mapper<List<News>, List<NewsEntity>>  newsEntityToNewsMapper) {
        this.newsRepository = newsRepository;
        this.appDatabase = appDatabase;
        this.newsToNewsEntityMapper = newsToNewsEntityMapper;
        this.newsEntityToNewsMapper = newsEntityToNewsMapper;
    }

    @Override
    public  void setCategoryNews(CategoryNews categoryNews){
        this.categoryNews = categoryNews.toString();
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveData(List<News> newsList) {
        Observable.fromIterable(newsToNewsEntityMapper.map(newsList)).subscribeOn(Schedulers.io()).subscribe(a -> appDatabase.newsDao().insertAll(a));
    }

    @Override
    public Observable<List<News>> getNewsFromDatabase() {
        return appDatabase.newsDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(newsEntityToNewsMapper::map).toObservable();
    }

    @Override
    public Observable<List<News>> getNews() {
        if (categoryNews.equals("undefined")) return newsRepository.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return newsRepository.getNews(categoryNews,"us")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
