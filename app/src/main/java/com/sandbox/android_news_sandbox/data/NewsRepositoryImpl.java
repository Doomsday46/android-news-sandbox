package com.sandbox.android_news_sandbox.data;

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
        return newsApi.getTopNews(category, country, API_KEY).flatMap(dto -> Observable.fromArray(mapper.map(dto)));
    }
}
