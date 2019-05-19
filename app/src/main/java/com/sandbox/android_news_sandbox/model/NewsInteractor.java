package com.sandbox.android_news_sandbox.model;

import com.sandbox.android_news_sandbox.presentation.CategoryNews;

import java.util.List;

import io.reactivex.Observable;

public interface NewsInteractor {
    Observable<List<News>> getNews();
    void setCategoryNews(CategoryNews categoryNews);
    void saveData(List<News> newsList);
    Observable<List<News>> getNewsFromDatabase();
}
