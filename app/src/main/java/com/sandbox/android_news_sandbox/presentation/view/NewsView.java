package com.sandbox.android_news_sandbox.presentation.view;

import android.view.View;

import com.sandbox.android_news_sandbox.model.News;

import java.util.List;

import io.reactivex.Observable;


public interface NewsView  {
    void setNews(List<News> news);
    void update();

}
