package com.sandbox.android_news_sandbox.model;

import java.util.List;

import io.reactivex.Observable;

public interface NewsInteractor {

    Observable<List<News>> getNews();
}
