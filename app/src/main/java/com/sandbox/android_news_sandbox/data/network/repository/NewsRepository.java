package com.sandbox.android_news_sandbox.data.network.repository;

import com.sandbox.android_news_sandbox.model.News;

import java.util.List;

import io.reactivex.Observable;


public interface NewsRepository {
    Observable<List<News>> getNews();
    Observable<List<News>> getNews(String category);
    Observable<List<News>> getNews(String category, String country);

    Observable<List<News>> getEverythingNews();
    Observable<List<News>> getEverythingNews(String category);
    Observable<List<News>> getEverythingNews(String category, String country);
}
