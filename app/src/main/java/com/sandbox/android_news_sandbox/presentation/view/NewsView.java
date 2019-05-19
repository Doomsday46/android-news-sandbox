package com.sandbox.android_news_sandbox.presentation.view;

import com.sandbox.android_news_sandbox.model.News;

import java.util.List;


public interface NewsView  {
    void setNews(List<News> news);
    void update();

}
