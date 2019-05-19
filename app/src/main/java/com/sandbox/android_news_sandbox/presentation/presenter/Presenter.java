package com.sandbox.android_news_sandbox.presentation.presenter;

import com.sandbox.android_news_sandbox.model.News;
import com.sandbox.android_news_sandbox.presentation.CategoryNews;
import com.sandbox.android_news_sandbox.presentation.view.NewsView;

import java.util.List;

public interface Presenter {
    void init(CategoryNews categoryNews, NewsView newsView);
    void loadData();
    void saveNews(List<News> newsList);
    void loadDataFromDatabase();
}
