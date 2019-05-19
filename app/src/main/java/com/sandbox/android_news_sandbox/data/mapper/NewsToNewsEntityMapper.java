package com.sandbox.android_news_sandbox.data.mapper;

import com.sandbox.android_news_sandbox.data.database.NewsEntity;
import com.sandbox.android_news_sandbox.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsToNewsEntityMapper implements Mapper<List<NewsEntity>, List<News>> {

    @Override
    public List<NewsEntity> map(List<News> object) {
        List<NewsEntity> listNews = new ArrayList<>();

        for (News news : object) {

            NewsEntity newsEntity = new NewsEntity();

            newsEntity.setAuthor(news.getAuthor());
            newsEntity.setDate(news.getDate());
            newsEntity.setDescription(news.getDescription());
            newsEntity.setNameResource(news.getNameResource());
            newsEntity.setTitle(news.getTitle());
            newsEntity.setUrlArticle(news.getUrlArticle());
            newsEntity.setUrlToImage(news.getUrlImage());
            newsEntity.setText(news.getContent());

            listNews.add(newsEntity);
        }

        return listNews;
    }
}
