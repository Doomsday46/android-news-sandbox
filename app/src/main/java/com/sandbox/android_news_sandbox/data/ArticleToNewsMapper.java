package com.sandbox.android_news_sandbox.data;

import com.sandbox.android_news_sandbox.model.News;

import java.util.ArrayList;
import java.util.List;

public class ArticleToNewsMapper implements Mapper<List<News>, NewsDto> {

    @Override
    public List<News> map(NewsDto object) {
        List<News> listNews = new ArrayList<>();

        for (ArticleDto articleDto : object.getArticleDtoList()) {

            listNews.add(new News(articleDto.getSource().getName(),
                                articleDto.getAuthor(),
                                articleDto.getTitle(),
                                articleDto.getUrlToImage(),
                                articleDto.getText(),
                                articleDto.getDate()));
        }

        return listNews;
    }



}
