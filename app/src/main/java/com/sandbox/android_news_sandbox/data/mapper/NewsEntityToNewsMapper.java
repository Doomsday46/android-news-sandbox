package com.sandbox.android_news_sandbox.data.mapper;

import com.sandbox.android_news_sandbox.data.database.NewsEntity;
import com.sandbox.android_news_sandbox.data.network.dto.ArticleDto;
import com.sandbox.android_news_sandbox.di.NewsModule;
import com.sandbox.android_news_sandbox.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsEntityToNewsMapper implements Mapper<List<News>, List<NewsEntity>> {

    @Override
    public List<News> map(List<NewsEntity> object) {
        List<News> listNews = new ArrayList<>();

        for (NewsEntity articleDto : object) {

            listNews.add(new News(articleDto.getNameResource(),
                    articleDto.getAuthor(),
                    articleDto.getTitle(),
                    articleDto.getUrlToImage(),
                    articleDto.getUrlArticle(),
                    articleDto.getText(),
                    articleDto.getDescription(),
                    articleDto.getDate()));
        }

        return listNews;
    }
}
