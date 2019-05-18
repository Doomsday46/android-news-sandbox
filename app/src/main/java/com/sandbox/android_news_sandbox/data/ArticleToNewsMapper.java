package com.sandbox.android_news_sandbox.data;

import com.sandbox.android_news_sandbox.model.News;

import java.util.ArrayList;
import java.util.List;

public class ArticleToNewsMapper implements Mapper<List<News>, NewsDto> {

    @Override
    public List<News> map(NewsDto object) {
        List<News> listNews = new ArrayList<>();

        for (Article article : object.getArticleList()) {

            listNews.add(new News(article.getSource().getName(),
                                article.getAuthor(),
                                article.getTitle(),
                                article.getUrlToImage(),
                                article.getText(),
                                article.getDate()));
        }

        return listNews;
    }



}
