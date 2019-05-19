package com.sandbox.android_news_sandbox.data.network.repository;

import com.sandbox.android_news_sandbox.data.network.NetworkService;
import com.sandbox.android_news_sandbox.data.network.NewsApi;
import com.sandbox.android_news_sandbox.data.mapper.ArticleToNewsMapper;
import com.sandbox.android_news_sandbox.model.News;

import java.util.List;

import io.reactivex.Observable;

public class NewsRepositoryImpl implements NewsRepository {

    private final static String API_KEY = "f20fce94ca1a40f688f7197aa4f8e708";
    private final ArticleToNewsMapper mapper;

    public NewsRepositoryImpl() {
        mapper = new ArticleToNewsMapper();
    }

    @Override
    public Observable<List<News>> getNews() {
        NewsApi newsApi = NetworkService.getInstance().getNewsApi();
        return newsApi.getTopNews(API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }

    @Override
    public Observable<List<News>> getNews(String category) {
        NewsApi newsApi = NetworkService.getInstance().getNewsApi();
        return newsApi.getTopNews(category, API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }

    @Override
    public Observable<List<News>> getNews(String category, String country) {
        NewsApi newsApi = NetworkService.getInstance().getNewsApi();
        return newsApi.getTopNews(country, category, API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }

    @Override
    public Observable<List<News>> getEverythingNews() {
        NewsApi newsApi = NetworkService.getInstance().getNewsApi();
        return newsApi.getEverythingNews(API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }

    @Override
    public Observable<List<News>> getEverythingNews(String category) {
        NewsApi newsApi = NetworkService.getInstance().getNewsApi();
        return newsApi.getEverythingNews(category, API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }

    @Override
    public Observable<List<News>> getEverythingNews(String category, String country) {
        NewsApi newsApi = NetworkService.getInstance().getNewsApi();
        return newsApi.getTopNews(country, category, API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }
}
