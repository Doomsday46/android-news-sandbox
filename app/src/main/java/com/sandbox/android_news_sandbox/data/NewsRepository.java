package com.sandbox.android_news_sandbox.data;

import com.sandbox.android_news_sandbox.model.News;

import java.util.List;

import io.reactivex.Observable;


public interface NewsRepository {
    Observable<List<News>> getNews();
    Observable<List<News>> getNews(String category);
    Observable<List<News>> getNews(String category, String country);
}
